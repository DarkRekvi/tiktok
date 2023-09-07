package com.nlp.tic_tac_poe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nlp.tic_tac_poe.databinding.GameBoardBinding;

import java.util.ArrayList;

/*
* Этапы сохранения проекта в git
* git init - инициализация гита в проекта
* git add . добавить все файлы в гиту
* git commit -m "текст сообщения"
* */
    public class MainActivity extends AppCompatActivity {
    public GameBoardBinding binding;

    public ArrayList<Button> squares = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Вызываем родительский метод тот же
        super.onCreate(savedInstanceState);
        View.OnClickListener listener = (view) ->
        {
            Button btn = (Button) view;
            if (!btn.getText().toString().equals("")) return;
            if (GameInfo.isTurn)
            {
                btn.setText(GameInfo.firstSymbol);
                int [] comb = calcWinner(GameInfo.firstSymbol);
                if(comb != null) {
                    Toast.makeText
                            (getApplicationContext(),
                                    "The winner is " + GameInfo.firstSymbol,
                                    Toast.LENGTH_LONG).show();
                    highlight(comb);
                }
            }
            else
            {
                btn.setText(GameInfo.secondSymbol);
                int [] comb = calcWinner(GameInfo.secondSymbol);
                if(comb != null) {
                    Toast.makeText
                            (getApplicationContext(),
                                    "The winner is " + GameInfo.secondSymbol,
                                    Toast.LENGTH_LONG).show();
                    highlight(comb);
                }
            }
            //Если в кнопке нет текста то будет выходить
            GameInfo.isTurn = !GameInfo.isTurn;
            initClearBordBtn();
        };

        binding = GameBoardBinding.inflate(getLayoutInflater());
        //Ссылаемся на интерфейс
        setContentView(binding.getRoot());
        generateBoard(3,3,binding.board);
        setListenerToSquares(listener);

    }
    public void generateBoard (int rowCount, int columnCount, LinearLayout board)
    {
        for (int row= 0; row < rowCount;row++)
        {
            LinearLayout rowContainer = generateRow(columnCount);
            board.addView(rowContainer);
        }
    }
    private void setListenerToSquares(View.OnClickListener listener)
    {
        for (int i = 0; i < squares.size();i++)
        {
            squares.get(i).setOnClickListener(listener);
        }
    }

    private LinearLayout generateRow(int squaresCount)
    {
        LinearLayout rowContainer = new LinearLayout(getApplicationContext());
        rowContainer.setOrientation(LinearLayout.HORIZONTAL);
        rowContainer.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        for( int square = 0; square < squaresCount; square++)
        {
            //Создание кнопки
            Button button = new Button(getApplicationContext());
            //Установка цвета для кнопки
            button.setBackgroundTintList(
                    ContextCompat.getColorStateList
                    (getApplicationContext(),R.color.coral));
            button.setWidth(convertToPixel(50));
            button.setHeight(convertToPixel(90));
            //Добавляем
            rowContainer.addView(button);
            squares.add(button);
        }
        return rowContainer;
    }
    public int convertToPixel (int digit)
    {
        float density = getApplicationContext().
                getResources().getDisplayMetrics().density;
        return (int)(digit * density + 0.5); //
    }

    public int [] calcWinner(String symbol)
    {
        for (int i=0;i<GameInfo.winCombination.length;i++) {
            boolean findComb = true;
            for (int j = 0; j < GameInfo.winCombination[0].length; j++) {
                int index = GameInfo.winCombination[i][j];
                if (!squares.get(index).getText().toString().equals(symbol)) {
                    findComb = false;
                    break;
                }
            }
            if (findComb) return GameInfo.winCombination[i];
        }
        return null;
    }
    //[1,2,3]
    public void highlight(int [] comb) {
        for(int i = 0;i<comb.length;i++) {
             squares.get(comb[i]).setBackgroundTintList(
                    ContextCompat.getColorStateList
                            (getApplicationContext(), R.color.green));
        }
    }

    private void initClearBordBtn(){
        Button clearBtn = findViewById(R.id.clear_button);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Новая игра",
                        Toast.LENGTH_LONG).show();
                for(Button square: squares) {
                    square.setText("");
                    square.setBackgroundTintList(
                            ContextCompat.getColorStateList(
                                    getApplicationContext(),
                                    R.color.coral));
                }
            }
        });
    }
}