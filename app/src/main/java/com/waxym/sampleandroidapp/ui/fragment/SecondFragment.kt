package com.waxym.sampleandroidapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.waxym.sampleandroidapp.R
import com.waxym.sampleandroidapp.network.Request
import com.waxym.sampleandroidapp.ui.viewmodel.UserViewModel

class SecondFragment : Fragment(), View.OnClickListener {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener(this)

        val textView = view.findViewById<TextView>(R.id.textview_second)
        viewModel.users.observe(viewLifecycleOwner) {
            textView.text = "User fetched: ${it.count()}"
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_second -> {
                viewModel.user().observe(viewLifecycleOwner) {
                    when (it.status) {
                        Request.Status.SUCCESS ->
                            Snackbar.make(view, "Fetched users : ${it.data?.count() ?: 0}", Snackbar.LENGTH_LONG).show()
                        Request.Status.ERROR ->
                            Snackbar.make(view, "Error, see logs.", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}