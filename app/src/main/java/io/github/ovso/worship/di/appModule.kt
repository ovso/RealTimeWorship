package io.github.ovso.worship.di

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.gms.ads.AdRequest
import io.github.ovso.worship.R
import io.github.ovso.worship.view.ui.video.adapter.MainAdapter
import org.koin.dsl.module

val appModule = module {
    /*
        single { SearchRequest() }
        factory { MaleAdapter() }
        factory { FemaleAdapter() }
        factory { VideoAdapter() }
    */
    single { MainAdapter() }
    factory { AdRequest.Builder().build() }
    single {
        DividerItemDecoration(get(), DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(get(), R.drawable.all_rv_divider)!!)
        }
    }
}
