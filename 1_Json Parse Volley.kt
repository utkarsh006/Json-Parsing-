package com.example.jsonparsing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestTask
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    // creating a variable for our url
    val url = "https://jsonplaceholder.typicode.com/posts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener ( View.OnClickListener {
            downloadTask()
        })
    }

    fun downloadTask(){
      
        // creating a variable for our request queue and initializing it
        val queue = Volley.newRequestQueue(this)
        
       // creating a variable for request and initializing it with json object request
        val request = StringRequest(Request.Method.GET, url,
            { response ->
                val data = response.toString()
                val jArray = JSONArray(data)
                //Log.e("Array", jArray.toString())

                for (idx in 0..jArray.length()-1){
                    val jobject = jArray.getJSONObject(idx)
                    //Toast.makeText(this,jobject.toString(), Toast.LENGTH_SHORT).show()

                    //getting values from objects
                    val userId = jobject.getString("userId")
                    val id = jobject.getString("id")
                    val title = jobject.getString("title")
                    val body = jobject.getString("body")

                    Toast.makeText(this, userId.toString(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, title.toString(), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, body.toString(), Toast.LENGTH_SHORT).show()
                }

            }, {  })

        // at last we are adding the request to our queue.
        queue.add(request)
    }
}
