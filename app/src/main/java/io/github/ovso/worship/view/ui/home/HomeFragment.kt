package io.github.ovso.worship.view.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.worship.R
import io.github.ovso.worship.databinding.FragmentHomeBinding
import io.github.ovso.worship.extensions.getViewModelFactory
import io.github.ovso.worship.view.base.DataBindingFragment
import io.github.ovso.worship.view.ui.home.adapter.HomeAdapter

class HomeFragment : DataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
  override val viewModel: HomeViewModel by viewModels { getViewModelFactory() }

  private val adapter by lazy { HomeAdapter() }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    setupRv()
    observe()
  }

  private fun observe() {
    viewModel.items.observe(viewLifecycleOwner, Observer {
      adapter.submitList(it)
    })
  }

  private fun setupRv() {
    binding.rvHome.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
    with(binding.rvHome) {
      addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
      adapter = this@HomeFragment.adapter
    }
  }

}

/*
    <!--
    삼일교회
    분당우리교회
    우리들교회
    100주년기념교회
    선한목자교회
    수영로교회
    청파감리교회
    지구촌교회
    목포사랑의교회
    온누리교회
    호산나교회
    충현교회
    산정현교회
    성서대학교회
    영락교회
    광림교회
    그레이스한인교회
    남서울교회
    내수동교회
    내일교회
    베이직교회
    새문안교회
    성락성결교회
    소망교회
    임마누엘교회
    종암제일교회
    남포교회
    다드림교회
    대전중문교회
    동안교회
    만나교회
    명덕교회
    벧샬롬교회
    사람살리는교회
    서대문교회
    영안침례교회
    제주성안교회
    주안장로교회
    주은혜교회
    충신교회
    토론토서부장로교회
    하나교회
    행신교회
    노량진교회
    신반포교회
    대전새벽교회
    대흥교회
    동막교회
    은혜샘물교회
    한우리교회
    허브교회
    높은뜻연합선교회
    -->
*/
