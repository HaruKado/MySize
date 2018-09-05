package com.example.kadohiraharuki.mysize

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //スコープ関数を使い記述が簡単に
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            neck.setText(getString("NECK", ""))
            sleeve.setText(getString("SLEEVE",""))
            waist.setText(getString("WAIST",""))
            inseam.setText(getString("INSEAM",""))

        }
        save.setOnClickListener{onSaveTapped()}

        heightButton.setOnClickListener{
            startActivity<HighActivity>()
        }
    }
    private fun onSaveTapped(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        //sharedPreference.Editorのインターフェースのインスタンスを取得する必要がある
        val editor = pref.edit()
        editor.putString("NECK",neck.text.toString())
                .putString("SLEEVE",sleeve.text.toString())
                .putString("WAIST",waist.text.toString())
                .putString("INSEAM",inseam.text.toString())

                //applyメソッドで保存
                .apply()
    }
}
