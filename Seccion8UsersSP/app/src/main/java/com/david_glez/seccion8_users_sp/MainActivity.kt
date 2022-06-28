package com.david_glez.seccion8_users_sp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.david_glez.seccion8_users_sp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SP
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        if (isFirstTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this).setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { dialogInterface, i ->
                    val username = dialogView.findViewById<TextInputEditText>(R.id.tieName)
                        .text.toString()
                    with(preferences.edit()){
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_user), username).apply()
                    }
                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show()
                }
                .show()
        } else {
            val username = preferences.getString(getString(R.string.sp_user), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }

        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        //Inflar la vista en el recyclerView
        binding.recyclerView.apply {
            /* Optimizar el reimiento de la aplicacion, ya que en la vista se le esta indicando un
            * tamaño lo ideal es agregar esta propiedad*/
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    //LLenar la lista
    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val david = User(1, "David", "Gonzalez", "https://ih1.redbubble.net/image.1060780989.1021/pp,840x830-pad,1000x1000,f8f8f8.jpg")
        val alejandro = User(2, "Alejandro", "Quezada", "https://ih1.redbubble.net/image.1060780989.1021/pp,840x830-pad,1000x1000,f8f8f8.jpg")
        val juan = User(3, "Juan", "Gomez", "https://ih1.redbubble.net/image.1060780989.1021/pp,840x830-pad,1000x1000,f8f8f8.jpg")
        val Luis = User(4, "Luis", "Cruz", "https://ih1.redbubble.net/image.1060780989.1021/pp,840x830-pad,1000x1000,f8f8f8.jpg")

        users.add(david)
        users.add(alejandro)
        users.add(juan)
        users.add(Luis)
        users.add(david)
        users.add(alejandro)
        users.add(juan)
        users.add(Luis)
        users.add(david)
        users.add(alejandro)
        users.add(juan)
        users.add(Luis)
        users.add(Luis)
        users.add(david)
        users.add(alejandro)
        users.add(juan)
        users.add(Luis)
        users.add(Luis)
        users.add(david)
        users.add(alejandro)
        users.add(juan)
        users.add(Luis)
        return users
    }

    // Interface para poder hacer click en el recyclerView
    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }
}