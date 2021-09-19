package au.edu.swin.sdmd.core1


import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    public var Savedscore = 0;
    public var score = 0;

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LIFECYCLE", "onSaveInstanceState Called")
        outState.putInt("ResultSaved", Savedscore)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "created")


        val add = findViewById<Button>(R.id.add)
        val steal = findViewById<Button>(R.id.steal)
        val reset = findViewById<Button>(R.id.reset)
        val answer = findViewById<TextView>(R.id.score)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("ResultSaved", 0)
            answer.text = score.toString()
        }

        add.setOnClickListener {
            if (score < 15) {
                score++
                Savedscore = score
                answer.text = score.toString()
                changeColor(score, answer)
            }
        }
        steal.setOnClickListener {
            if (score > 0) {
                score--
                Savedscore = score
                answer.text = score.toString()
                changeColor(score, answer)
            }
        }
        reset.setOnClickListener {
            score = 0
            Savedscore = score
            answer.text = score.toString()
            changeColor(score, answer)
        }
    }

    private fun changeColor(score: Int, answer: TextView) {
        if (score in 5..9) {
            answer.setTextColor(Color.parseColor("#0000FF"))
        }
        if (score in 10..15) {
            answer.setTextColor(Color.parseColor("#00FF00"))
            if (score == 15) {
                var mediaPlayer = MediaPlayer.create(this, R.raw.battle_crowd_celebrate_stutter)
                mediaPlayer.start() // no need to call prepare(); create() does that for you
            }
        }
        if (score in 0..4) {
            answer.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
}