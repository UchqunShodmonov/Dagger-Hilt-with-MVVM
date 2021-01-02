package uz.uchqun.hilt_tutorial2.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uz.uchqun.hilt_tutorial2.databinding.ActivityMainBinding
import uz.uchqun.hilt_tutorial2.model.Blog
import uz.uchqun.hilt_tutorial2.utils.DataState

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }


    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            mBinding.text.text = message
        } else {
            mBinding.text.text = "Unknown error"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        mBinding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }


    private fun appendBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }

        mBinding.text.text = sb.toString()
    }
}