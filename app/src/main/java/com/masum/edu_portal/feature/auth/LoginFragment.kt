package com.masum.edu_portal.feature.auth


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.masum.edu_portal.BuildConfig
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseFragment
import com.masum.edu_portal.common.callback_listener.AuthenticationRequest
import com.masum.edu_portal.databinding.FragmentLoginBinding
import com.masum.edu_portal.feature.auth.datamodel.Member
import com.masum.edu_portal.feature.home.view.HomeActivity
import com.masum.edu_portal.feature.launch.view.LauncherActivity
import com.masum.edu_portal.networks.HTTP_PARAM
import com.masum.edu_portal.repository.AuthRepository
import com.masum.edu_portal.utils.SharedPreferencesEnum
import com.masum.edu_portal.viewmodel.MyViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import okhttp3.MultipartBody


class LoginFragment : BaseFragment() , InstallStateUpdatedListener {



    companion object {
        const val MY_REQUEST_CODE = 100
        private const val REQUEST_CODE_FLEXI_UPDATE = 17362

    }

   override fun onStateUpdate(installState: InstallState) {
        if (installState.installStatus() == InstallStatus.DOWNLOADED) {
            Log.e("","download complete")
            showUpdateSnackbar()
        }
    }

    private lateinit var appUpdateManager: AppUpdateManager

    private val updateAvailable = MutableLiveData<Boolean>().apply { value = false }
    private lateinit var updateInfo: AppUpdateInfo

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var listener: AuthenticationRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentLoginBinding>(
                inflater,
                R.layout.fragment_login!!, container, false
            )

        mActivity = activity
        return binding!!.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity as LauncherActivity).get(MyViewModel::class.java)
        listener = AuthRepository()
        checkMyUpdate()

        binding.btnLogin.setOnClickListener {
            val memberId = binding.etMemberId.getText().toString()
            if (!isValid(binding.etPhone.getText().toString(), memberId)) {
                return@setOnClickListener
            }

            val phonNumber = "+880" + binding.etPhone.getText().toString()
            if (phonNumber.length > 14 || phonNumber.length < 14) {
                showErrorDialog(
                    "Invalid number: $phonNumber",
                    "Mobile number is invalid,Please use your valid mobile number."
                )
                return@setOnClickListener
            }
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart(HTTP_PARAM.MEMBERSHIP_ID, memberId)
            builder.addFormDataPart(HTTP_PARAM.MOBILE_NO, phonNumber)

            viewModel.checkAuthentication(builder, listener)

        }

        viewModel.authData.observe(mActivity as LauncherActivity, object : Observer<Member> {
            override fun onChanged(t: Member) {
                SharedPreferencesEnum.getInstance(mActivity)!!
                    .put(SharedPreferencesEnum.Key.PROFILE_IMG, t.image)
                startActivity(Intent(mActivity, HomeActivity::class.java))
                mActivity!!.finish()
            }

        })

        viewModel.isProgressLoad.observe(this, object : Observer<Boolean> {
            override fun onChanged(isProgressLoad: Boolean) {
                Log.e("", "")
                if (isProgressLoad) {
                    Log.e("", "")
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        showProgressDialog()
                    }

                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        hideProgressDialog()
                    }
                }

            }
        })


        viewModel.isDataLoadFailed.observe(this, object : Observer<String> {
            override fun onChanged(errorText: String) {

            }
        })
    }


    private fun checkMyUpdate(){

        appUpdateManager = AppUpdateManagerFactory.create(mActivity)
        appUpdateManager.registerListener(this)
        et_member_id.setText( BuildConfig.VERSION_CODE.toString())

        appUpdateManager.appUpdateInfo.addOnCompleteListener {
            Log.e("update","GET Info complete")
        }

        appUpdateManager.appUpdateInfo.addOnFailureListener {
            Log.e("update","GET Info failed ${it.message}")
        }

        checkForUpdate()

        updateAvailable.observe(this, Observer {
            et_member_id.setText(
                if (it) R.string.update_available else R.string.update_not_available
            )
        })

        btn_login.setOnClickListener {
            if (updateAvailable.value == true) {
                startForInAppUpdate(updateInfo)
            }
        }
    }
    private fun isValid(username: String, password: String): Boolean {
        if (username == "" || password == "") {
            showErrorDialog(
                "Error",
                "User Phone number or Member Id can't be empty,Please fill up and try again.."
            )
            return false
        }
        return true
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }



    override fun onResume() {
        super.onResume()
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                Log.e("","in onResume, download complete")
                showUpdateSnackbar()
            } else {
                Log.e("update","${it.installStatus()}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        appUpdateManager.unregisterListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("update","request code: $requestCode, result code: $resultCode")
    }

    private fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                it.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                updateInfo = it
                updateAvailable.value = true
                Log.e("update","Version code available ${it.availableVersionCode()}")
            } else {
                updateAvailable.value = false
                Log.e("update","Update not available")
            }
        }
    }

    private fun startForInAppUpdate(it: AppUpdateInfo) {
        appUpdateManager.startUpdateFlowForResult(
            it,
            AppUpdateType.FLEXIBLE,
            mActivity,
            REQUEST_CODE_FLEXI_UPDATE
        )
    }

    private fun showUpdateSnackbar() {
        Snackbar
            .make(et_phone, R.string.restart_to_update, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.action_restart) { appUpdateManager.completeUpdate() }
            .show()
    }




}


