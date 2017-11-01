package www.ittepic.edu.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   EditText edit;
    TextView text;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText)findViewById(R.id.editText);
        text = (TextView) findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hilo h = new hilo(text, Integer.parseInt(String.valueOf(edit.getText())));
                h.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }
}
class hilo extends AsyncTask<Void, Integer, Boolean>{
     int numero;
     int aux;
     TextView tex;
    public hilo(TextView t, int num){
        tex = t;
        numero = num;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        for(int i = 0; i<10; i++){
            publishProgress(i);
            retrasoApreciable();
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tex.setText("");
    }
    private void retrasoApreciable() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean){
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tex.setText(tex.getText()+" "+numero+"*"+values[0].intValue()+" = "+(numero*values[0].intValue())+"\n");
    }

    @Override
    protected void onCancelled() {
    }
}

