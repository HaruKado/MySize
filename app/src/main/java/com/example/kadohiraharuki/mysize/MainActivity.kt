package com.example.kadohiraharuki.mysize

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //共有プリファレンスを使って値を取得
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //スコープ関数applyを使い記述を簡略化できる
        pref.apply {
            //getString関数を使いedit変数に値を代入（null許容型）
            val editNeck = getString("NECK", "")
            val editSleeve = getString("SLEEVE", "")
            val editWaist = getString("WAIST", "")
            val editInseam = getString("INSEAM", "")

            //string型を単純には代入できないのでCharaSequence型をとるsetTextメソッドを使用
            neck.setText(editNeck)
            sleeve.setText(editSleeve)
            waist.setText(editWaist)
            inseam.setText(editInseam)
        }
        save.setOnClickListener{onSaveTapped()}
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
