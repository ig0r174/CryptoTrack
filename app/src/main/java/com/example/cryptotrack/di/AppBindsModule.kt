package com.example.cryptotrack.di

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.CoinInfoRepository
import com.example.data.repository.CoinRepository
import com.example.data.storage.IStorage
import com.example.data.storage.Storage
import com.example.domain.repository.ICoinInfoRepository
import com.example.domain.repository.ICoinsRepository
import com.example.domain.useCases.GetCoinInfo.GetCoinInfoUseCase
import com.example.domain.useCases.GetCoinInfo.IGetCoinInfoUseCase
import com.example.domain.useCases.GetCoins.GetCoinsUseCase
import com.example.domain.useCases.GetCoins.IGetCoinsUseCase
import com.example.cryptotrack.viewmodels.*
import com.example.network.retrofit.ApiProvider
import com.example.network.retrofit.IApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [AppModule::class])
abstract class AppBindsModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinDetailsViewModel::class)
    abstract fun bindCoinDetailViewModel(coinDetailsViewModel: CoinDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds abstract fun bindStorage(storage: Storage): IStorage

    @Binds abstract fun bindCoin(coin: CoinRepository): ICoinsRepository
    @Binds abstract fun bindCoinUseCase(coinsUseCase: GetCoinsUseCase): IGetCoinsUseCase

    @Binds abstract fun bindCoinInfo(coinInfo: CoinInfoRepository): ICoinInfoRepository
    @Binds abstract fun bindCoinInfoUseCase(coinsInfoUseCase: GetCoinInfoUseCase): IGetCoinInfoUseCase

}
@Module
class AppModule(private val context: Context) {
    @Provides fun provideContext() = context
    @Provides
    fun provideApi(apiProvider: ApiProvider): IApi =
        apiProvider.getApi()
    @Provides
    fun provideSharedPref():SharedPreferences {
        return context.getSharedPreferences("appPrefs", 0)
    }
}