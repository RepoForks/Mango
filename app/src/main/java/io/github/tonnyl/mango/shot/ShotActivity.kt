package io.github.tonnyl.mango.shot

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.tonnyl.mango.R

/**
 * Created by lizhaotailang on 2017/6/28.
 */

class ShotActivity : AppCompatActivity() {

    private lateinit var mShotFragment: ShotFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container)

        if (savedInstanceState != null) {
            mShotFragment = supportFragmentManager.getFragment(savedInstanceState, ShotFragment::class.java.simpleName) as ShotFragment
        } else {
            mShotFragment = ShotFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, mShotFragment, ShotFragment::class.java.simpleName)
                .commit()

        ShotPresenter(mShotFragment)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (mShotFragment.isAdded) {
            supportFragmentManager.putFragment(outState, ShotFragment::class.java.simpleName, mShotFragment)
        }
    }

}