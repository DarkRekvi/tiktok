package com.nlp.tic_tac_poe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
* Этапы сохранения проекта в git
* git init - инициализация гита в проекта
* git add . добавить все файлы в гиту
* git commit -m "текст сообщения"
* */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Вызываем родительский метод тот же
        super.onCreate(savedInstanceState);
        //Ссылаемся на интерфейс
        setContentView(R.layout.activity_main);
        //Задаем ссылки на элементы интерфейса
        Button showContentButton = findViewById(R.id.content_button);
        EditText contentInput = findViewById(R.id.content_input);
        showContentButton.setOnClickListener((view) ->{
            String contentStr = contentInput.getText().toString();
            //Если в поле contentInput
            // нету текста, то выходим из метода
            if(contentStr.equals("")) return;
            Toast toast = Toast.makeText(this, contentStr, Toast.LENGTH_LONG);
            toast.show();
        });

    }

}