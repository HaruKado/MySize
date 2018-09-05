package com.example.kadohiraharuki.mysize

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_high.*

class HighActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high)

        //spiner関数を読み出すためにonItemSelectedプロパティを使う
        spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    //patternは選択が発生した親ビュー、ここではスピナー。Adapterview明記なし
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //viewは選択された項目そのもの、型が一致すればspiner型を返す
                        val spinner = parent as? Spinner
                        val item = spinner?.selectedItem as? String
                        item?.let {
                            if (it.isNotEmpty()) height.text = it
                        }
                    }
                    //項目が選択されずにスピナーが閉じられた時に呼ばれる
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    }
                }
        //共有プリファレンスかつスコープ関数でインテント
        PreferenceManager.getDefaultSharedPreferences(this).apply {
            val heightval = getInt("HEIGHT", 160)
            //heightの型を型にtext変換にし代入
            height.text = heightval.toString()
            //シークバーに取得した直を設定、seekbarはprogressプロパティでアクセス
            seekBar.progress = heightval
        }
        seekBar.setOnSeekBarChangeListener(
                //シークバーを操作した時のイベントリスナーを登録
                object : SeekBar.OnSeekBarChangeListener {
                    //シークバーの値を変更した時の処理を記述
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        //シークバーの値をtextviewに表示
                        height.text = progress.toString()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

                })

        //ラジオグループ実装
        //ラジオの選択状態が変更された時に呼ばれるリスナーを登録
        radioGroup.setOnCheckedChangeListener{
            //引数checkedId選択されたラジオボタンのIDが渡される
            group, checkedId->
            //findViewByIdを使ってラジオボタンを取得し文字列をtextviewで表示
            height.text = findViewById<RadioButton>(checkedId).text
            }

        }


    //onPauseメソッドはアクティビティが非表示になる時に呼ばれる
    override fun onPause() {
        //onPause：アクティビティが非表示になる時に呼ばれる
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this).edit ()
                .putInt("Height",height.text.toString().toInt())
                .apply()
    }
}
