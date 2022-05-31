package com.example.cryptotrack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptotrack.ui.fragments.HomeFragment

class MainActivity : AppCompatActivity(), HomeFragment.OnCoinClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCoinClick(coinId: Int) {
        //Log.e("e", coinId.toString())
        /*
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_host, CoinDetailFragment.newInstance(coinId))
            .addToBackStack(null)
        fragmentTransaction.hide(mCoinListFragment)
        fragmentTransaction.commit()*/
    }
}