package com.example.onehelp

//Memanggil FirstView
class FirstPresenterImp(model : FirstView) : FirstPresenter {
    var viewmodel : FirstView? = null

    init {
        viewmodel = model
    }

    override fun tambahData(msg: String) {
        //Check inputan
        if(msg.isNotEmpty()){
            var model = ModelMVP(msg)
            //Add ke view
            viewmodel?.berhasil(model)
        }

        else{
            //Jika data kosong
            viewmodel?.error()
        }

    }
}