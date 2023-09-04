package com.nlp.tic_tac_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    public ArrayList<Button> squaresArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Вызываем родительский метод тот же
        super.onCreate(savedInstanceState);
        binding = GameBoardBinding.inflate(getLayoutInflater());
        //Ссылаемся на интерфейс
        setContentView(binding.getRoot());
        boardInit();

    }
    public void boardInit(){

        squaresArray = new ArrayList<>();
        squaresArray.add(binding.field1);
        squaresArray.add(binding.field2);
        squaresArray.add(binding.field3);
        squaresArray.add(binding.field4);
        squaresArray.add(binding.field5);
        squaresArray.add(binding.field6);
        squaresArray.add(binding.field7);
        squaresArray.add(binding.field8);
        squaresArray.add(binding.field9);

        for(int i = 0; i < squaresArray.size();i++){
            int finalI = i;
            squaresArray.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!squaresArray.get(finalI).getText().toString().equals(" "))
                        return;
                    if(GameInfo.whoIsNext)
                        squaresArray.get(finalI).setText(GameInfo.firstPlayerSymbol);
                    else
                        squaresArray.get(finalI).setText(GameInfo.secondPlayerSymbol);
                    GameInfo.whoIsNext = !GameInfo.whoIsNext;
                }
            });
        }
    }

}