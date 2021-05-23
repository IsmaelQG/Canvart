package com.example.canvart.ui.challenges

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.canvart.R
import com.example.canvart.data.database.AppDatabase
import com.example.canvart.data.entity.Challenge
import com.example.canvart.data.enums.ChallengeType
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material
import com.example.canvart.databinding.FragmentChallengeDoneBinding
import com.example.canvart.utils.delete
import com.example.canvart.utils.viewBinding
import kotlinx.android.synthetic.main.fragment_challenge_done.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ChallengeDoneFragment(var url: String, var seconds: Int) : Fragment(R.layout.fragment_challenge_done) {

    private var imageCapture: ImageCapture? = null

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private val binding by viewBinding { FragmentChallengeDoneBinding.bind(it)}

    private val viewModel : ChallengeDoneViewModel by viewModels {
        ChallengeDoneViewModelFactory(
                AppDatabase.getInstance(requireContext()).challengeDao,
                AppDatabase.getInstance(requireContext()).imageURLDao,
                requireActivity().getPreferences(Context.MODE_PRIVATE)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.camera = true

        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                    requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // Set up the listener for take photo button
        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()

        observers()
        listeners()

    }

    private fun observers(){
        binding.btnCamera.setOnClickListener {
            takePhoto()
            viewModel.switchCamera()
        }
        binding.btnRepeat.setOnClickListener {
            viewModel.switchCamera()
            binding.imgDrawing.setImageDrawable(null)
            val file = File(viewModel.getUri())
            file.delete(requireContext())
        }
        binding.btnSubmit.setOnClickListener {
            viewModel.saveChallengeImage(
                    Challenge(
                          0,
                            viewModel.difficulty,
                            viewModel.material,
                            1,
                            true,
                            "Reto de Imagen",
                            ChallengeType.CUSTOM,
                            null
                    ),
                    url,
                    binding.rtScore.rating.toDouble(),
                    binding.txtDescription.text.toString()
            )
            requireActivity().supportFragmentManager.popBackStack(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    private fun listeners(){
        viewModel.cameraChecker.observe(viewLifecycleOwner, Observer { result ->
            binding.camera = result
        })
        viewModel.uri.observe(viewLifecycleOwner, Observer { result ->
            Glide.with(requireContext())
                    .load(Uri.parse(result))
                    .centerCrop()
                    .into(binding.imgDrawing)
            //binding.imgDrawing.setImageURI(Uri.parse(result))
        })
        viewModel.difficultyLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            when(result){
                0 -> viewModel.difficulty = Difficulty.EASY
                1 -> viewModel.difficulty = Difficulty.MEDIUM
                2 -> viewModel.difficulty = Difficulty.HARD
            }
        })
        viewModel.materialLiveData.observe(viewLifecycleOwner, Observer {
            result ->
            when(result){
                0 -> viewModel.material = Material.PENCIL
                1 -> viewModel.material = Material.PEN
                2 -> viewModel.material = Material.MARKER
            }
        })
    }

    //CAMERA

    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        // Create time-stamped output file to hold the image
        val photoFile = File(
                outputDirectory,
                SimpleDateFormat(FILENAME_FORMAT, Locale.US
                ).format(System.currentTimeMillis()) + ".jpg")

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
                outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                viewModel.setUri(savedUri)
                val msg = "Photo capture succeeded: $savedUri"
                Log.d(TAG, msg)
            }
        })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(viewFinder.surfaceProvider)
                    }

            imageCapture = ImageCapture.Builder()
                    .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture)

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun allPermissionsGranted() : Boolean {
        return (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else requireActivity().filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onRequestPermissionsResult(
            requestCode: Int, permissions: Array<String>, grantResults:
            IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireContext(),
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }
    }

    companion object{

        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        fun newInstance(url: String, seconds: Int) : ChallengeDoneFragment =
            ChallengeDoneFragment(url, seconds)

    }

}