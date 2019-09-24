package timerdemo.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> answers = new ArrayList<Integer>();

    Button button;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;

    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    TextView secondTextView;

    RelativeLayout gameRelativeLayout;

    int locationOfCorrectAnswer;

    int score = 0;
    int numberOfQuestions = 0;

    public void playAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);

        score =0;
        numberOfQuestions = 0;
        secondTextView.setText("30s");
        pointsTextView.setText("0 / 0");
        resultTextView.setText("");

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                secondTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                secondTextView.setText("0s");
                resultTextView.setText("Your score : " + Integer.toString(score));

            }
        }.start();
    }


    public void generateQuestion(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i=0 ; i<4 ; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
            }else{
                incorrectAnswer = random.nextInt(41);
                while(incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }


    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!  ");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    public void start(View view){
        button.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.againButton));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.startButton);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgain = (Button) findViewById(R.id.againButton);

        sumTextView = (TextView) findViewById(R.id.sumTextview);
        resultTextView = (TextView) findViewById(R.id.resultTextview);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        secondTextView = (TextView) findViewById(R.id.secondTextView);

        gameRelativeLayout = (RelativeLayout)  findViewById(R.id.gameRelativeLayout);




    }
}
