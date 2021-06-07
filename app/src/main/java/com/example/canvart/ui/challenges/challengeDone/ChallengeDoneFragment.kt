package com.example.canvart.ui.challenges.challengeDone

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.KeyListener
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
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
import com.example.canvart.data.enums.Timer
import com.example.canvart.databinding.FragmentChallengeDoneBinding
import com.example.canvart.utils.viewBinding
import kotlinx.android.synthetic.main.fragment_challenge_done.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList


private const val URL = "URL"
private const val LIST_TEXT = "LIST_PORTRAIT"
private const val COND_CHALLENGE = "COND_CHALLENGE"
private const val ID_CHALLENGE = "ID_CHALLENGE"

class ChallengeDoneFragment : Fragment(R.layout.fragment_challenge_done) {

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
            requestPermissions(
                    requireActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }
        startCamera()

        // Set up the listener for take photo button
        outputDirectory = getOutputDirectory()

        cameraExecutor = Executors.newSingleThreadExecutor()

        observers()
        listeners()
    }

    private fun listeners(){
        binding.btnCamera.setOnClickListener {
            takePhoto()
            viewModel.switchCamera()
        }
        binding.btnRepeat.setOnClickListener {
            viewModel.switchCamera()
            binding.imgDrawing.setImageDrawable(null)
        }
        binding.btnSubmit.setOnClickListener {
            when(requireArguments().getInt(COND_CHALLENGE, 0)){
                1 ->{
                    viewModel.saveChallengeImage(
                            Challenge(
                                    0,
                                    viewModel.difficulty,
                                    viewModel.material,
                                    viewModel.timer,
                                    1,
                                    true,
                                    "Reto de Imagen",
                                    ChallengeType.CUSTOM,
                                    null
                            ),
                            requireArguments().getString(URL, ""),
                            binding.rtScore.rating.toDouble(),
                            binding.txtDescription.text.toString()
                    )
                }
                2 ->{
                    viewModel.saveChallengePortrait(
                            Challenge(
                                    0,
                                    viewModel.difficulty,
                                    viewModel.material,
                                    viewModel.timer,
                                    1,
                                    true,
                                    "Reto de Retrato",
                                    ChallengeType.CUSTOM,
                                    null
                            ),
                            requireArguments().getIntegerArrayList(LIST_TEXT)?.toList()!!,
                            binding.rtScore.rating.toDouble(),
                            binding.txtDescription.text.toString()
                    )
                }
                3 ->{
                    viewModel.saveChallengeDescription(
                            Challenge(
                                    0,
                                    viewModel.difficulty,
                                    viewModel.material,
                                    viewModel.timer,
                                    1,
                                    true,
                                    "Reto de Descripción",
                                    ChallengeType.CUSTOM,
                                    null
                            ),
                            requireArguments().getIntegerArrayList(LIST_TEXT)?.toList()!!,
                            binding.rtScore.rating.toDouble(),
                            binding.txtDescription.text.toString()
                    )
                }
                4 ->{
                    viewModel.redoChallengeImage(
                        requireArguments().getLong(ID_CHALLENGE, 0),
                        binding.rtScore.rating.toDouble(),
                        binding.txtDescription.text.toString()
                    )
                }
                5 ->{
                    viewModel.redoChallengePortrait(
                        requireArguments().getLong(ID_CHALLENGE, 0),
                        binding.rtScore.rating.toDouble(),
                        binding.txtDescription.text.toString()
                    )
                }
                6 ->{
                    viewModel.redoChallengeDescription(
                        requireArguments().getLong(ID_CHALLENGE, 0),
                        binding.rtScore.rating.toDouble(),
                        binding.txtDescription.text.toString()
                    )
                }
            }

            requireActivity().supportFragmentManager.popBackStack(
                    null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        binding.btnCancel.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observers(){
        viewModel.cameraChecker.observe(viewLifecycleOwner, Observer { result ->
            binding.camera = result
        })
        viewModel.uri.observe(viewLifecycleOwner, Observer { result ->
            Glide.with(requireContext())
                    .load(Uri.parse(result))
                    .centerCrop()
                    .into(binding.imgDrawing)
        })
        viewModel.difficultyLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                0 -> viewModel.difficulty = Difficulty.EASY
                1 -> viewModel.difficulty = Difficulty.MEDIUM
                2 -> viewModel.difficulty = Difficulty.HARD
            }
        })
        viewModel.materialLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                0 -> viewModel.material = Material.PENCIL
                1 -> viewModel.material = Material.PEN
                2 -> viewModel.material = Material.MARKER
            }
        })
        viewModel.timerLiveData.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                0 -> viewModel.timer = Timer.ONE_MIN
                1 -> viewModel.timer = Timer.TWO_MIN
                2 -> viewModel.timer = Timer.FIVE_MIN
                3 -> viewModel.timer = Timer.TEN_MIN
                4 -> viewModel.timer = Timer.THIRTY_MIN
                5 -> viewModel.timer = Timer.INFINITE
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

    companion object{

        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

        fun newInstance(cond: Int, url: String) : ChallengeDoneFragment =
            ChallengeDoneFragment().apply {
                arguments = bundleOf(COND_CHALLENGE to cond, URL to url)
            }

        fun newInstance(cond: Int, list_portrait: List<Int>) : ChallengeDoneFragment =
                ChallengeDoneFragment().apply {
                    arguments = bundleOf(COND_CHALLENGE to cond, LIST_TEXT to ArrayList(list_portrait))
                }

        fun newInstance(cond: Int, idChallenge : Long) : ChallengeDoneFragment =
            ChallengeDoneFragment().apply {
                arguments = bundleOf(COND_CHALLENGE to cond, ID_CHALLENGE to idChallenge)
            }

    }

}