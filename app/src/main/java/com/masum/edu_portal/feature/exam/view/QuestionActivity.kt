package com.masum.edu_portal.feature.exam.view

import android.os.Build
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.masum.edu_portal.DataResource
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.common.Constant
import com.masum.edu_portal.databinding.ActivityQuestionBinding
import com.masum.edu_portal.di.ViewModelProviderFactory
import com.masum.edu_portal.feature.exam.data.question.Datum
import com.masum.edu_portal.myviewmodel.ExamViewModel
import kotlinx.android.synthetic.main.activity_question.*
import javax.inject.Inject

class QuestionActivity : BaseActivity() {
    lateinit var binding: ActivityQuestionBinding

    @Inject
    lateinit var examViewModel: ExamViewModel
    lateinit var examId: String
    lateinit var subjectId: String
    private var questionList = ArrayList<Datum>()
    private var cTimer: CountDownTimer? = null
    private var countQuestion = 0;
    private var scoreCount: Int = 0
    private var wrngCount: Int = 0
    private var option = arrayOfNulls<String>(4)
    private var ansArray = arrayOfNulls<String>(4)
    private var corrctAns: Int = 0;

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_question
    }

    override fun initComponent() {
        binding = getBinding() as ActivityQuestionBinding
        initToolbar()
        enableBackButton()
        examViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(ExamViewModel::class.java)

        setOnlyStatusBarTransparent()
        getIntentData()
    }

    override fun initFunctionality() {
        if (examId != null) {
            examViewModel.getExamQuestionListData(examId)
            observeExamQuestionData()
        } else {
            examViewModel.getQuestionListData(subjectId)
            observeQuestionBankData()
        }
    }

    override fun initListener() {

        binding.btnNext.setOnClickListener {
            checkAns()
            countQuestion++
            cancelTimer()
            startTimer(countQuestion)

        }

        binding.btnFinishExam.setOnClickListener {
            showFinishAlertDialog("Finish Exam?", "Do you want to exit from this exam now ?")
        }

        binding.btnFinish.setOnClickListener {
            finish()
        }
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
    }

    private fun observeExamQuestionData() {
        examViewModel.examQuestionList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showProgressDialog()
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            // showErrorDialog("Failed!", dataResource.message)
                            hideProgressDialog()
                        }
                    }

                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideProgressDialog()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!questionList.isEmpty()) {
                                questionList.clear()
                            }
                            questionList.addAll(dataResource.data!!.data!!.data!!)
                            setToolbarTitle(questionList[0].chapterTitle)
                            startTimer(countQuestion)
                        }
                    }
                }
            }
        })
    }


    private fun observeQuestionBankData() {
        examViewModel.questionBankList.observe(this, Observer { dataResource ->
            if (dataResource != null) {
                when (dataResource.status) {
                    DataResource.DataStatus.LOADING -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            showProgressDialog()
                        }
                    }
                    DataResource.DataStatus.ERROR -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            // showErrorDialog("Failed!", dataResource.message)
                            hideProgressDialog()
                        }
                    }

                    DataResource.DataStatus.SUCCESS -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            hideProgressDialog()
                        }
                        if (dataResource.data!!.data != null) {
                            if (!questionList.isEmpty()) {
                                questionList.clear()
                            }
                            questionList.addAll(dataResource.data!!.data!!.data!!)
                            setToolbarTitle(questionList[0].bankTitle)
                        }
                    }
                }
            }
        })
    }

    //start timer function
    private fun startTimer(count: Int) {
        var timer: Long = 60 * 1000
        if (countQuestion >= questionList.size) {
            binding.containExamDetail.visibility = View.VISIBLE
            binding.containQuestion.visibility = View.GONE
            binding.tvScore.text = "Total Score : " + scoreCount
            binding.tvWrong.text = "Wrong ans:" + wrngCount
            binding.tvQ.text = "Total Question: " + questionList.size
            return
        }
        binding.tvQuestion.text = questionList[count].questionTitle
        binding.tvTopic.text = questionList[count].topicTitle
        binding.tvQuestionAmount.text = "" + questionList.size
        option = arrayOfNulls<String>(4)
        ansArray = arrayOfNulls<String>(4)
        option = questionList[count].optionTitle!!.split(",").toTypedArray()
        ansArray = questionList[count].isAnswer!!.split(",").toTypedArray()
        rb_one.text = option[0]
        rb_two.text = option[1]
        rb_three.text = option[2]
        rb_four.text = option[3]

        var qOne = ansArray[0]
        var qtwo = ansArray[1]
        var qthree = ansArray[2]
        var qfour = ansArray[3]
        if (qOne!!.equals("1")) {
            corrctAns = ansArray[0]!!.toInt()
        } else if (qtwo!!.equals("1")) {
            corrctAns = ansArray[1]!!.toInt()

        } else if (qthree!!.equals("1")) {
            corrctAns = ansArray[2]!!.toInt()

        } else if (qfour!!.equals("1")) {
            corrctAns = ansArray[3]!!.toInt()
        }


        var quesNo = countQuestion + 1
        binding.tvQuestionNo.text = "" + quesNo + "/"

        cTimer = object : CountDownTimer(timer!!, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvCountdown.setText("" + millisUntilFinished / 1000);
            }

            override fun onFinish() {
                Log.e("", "")
                startTimer(countQuestion++)
                checkAns()

            }
        }
        (cTimer as CountDownTimer).start()
    }

    private fun checkAns() {
        if (binding.rbGroup.checkedRadioButtonId==-1){
            return
        }

        var rbID = binding.rbGroup.checkedRadioButtonId
        var isTrue = false
        if (rbID != null) {
            var radioButton = findViewById(rbID) as RadioButton
            var value = radioButton.text.toString()
            /*  for (i in option) {

              }*/

            if (option[corrctAns]!!.equals(value)) {
                isTrue = true
                corrctAns++

                Log.e(
                    "data",
                    "Correct: " + option[corrctAns] + " selected value " + value + ": true"
                )

            } else {
                isTrue = false
                wrngCount++
                Log.e(
                    "data",
                    "Correct: " + option[corrctAns] + " selected value: " + value + " : false"
                )
            }

        }

        binding.rbGroup.clearCheck()
    }


    //cancel timer
    fun cancelTimer() {
        if (cTimer != null) cTimer!!.cancel()
    }

    private fun getIntentData() {
        var bundle = intent.extras
        examId = bundle!!.getString(Constant.EXAM_KEY).toString()
        subjectId = bundle!!.getString(Constant.SUBJECT_KEY).toString()
    }

    override fun onStop() {
        super.onStop()
        cancelTimer()
    }

}