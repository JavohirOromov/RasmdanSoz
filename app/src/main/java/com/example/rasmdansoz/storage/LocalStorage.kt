package com.example.rasmdansoz.storage;
import android.content.Context
import android.content.SharedPreferences
/**
 * Creator: Javohir Oromov
 * Project: RasmdanSo'z
 * Date: 26/03/25
 * Javohir's MacBook Air
 */
class LocalStorage private constructor(context: Context){
    private val preferences: SharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    companion object{
        private const val FILE_NAME = "Javohir's RasmdanSo'z"
        private const val INDEX = "index"
        private const val COIN = "coin"
        private const val ANSWER = "answer"
        private const val CORRECT_INDEXES = "correct_indexes"
        private const val SAVED_VARIANTS = "saved_variants"
        private const val RESUME = "res"
        private lateinit var instance: LocalStorage

        fun init(context: Context){
            if (!(Companion::instance.isInitialized)){
                instance = LocalStorage(context)
            }
        }
        fun getInstance(): LocalStorage {
            return instance
        }
    }
    fun saveIndex(index: Int){
        editor.putInt(INDEX,index).apply()
    }
    fun getIndex(): Int{
        return preferences.getInt(INDEX,0)
    }
    fun saveCoin(coin: Int){
        editor.putInt(COIN,coin).apply()
    }
    fun getCoin(): Int{
        return preferences.getInt(COIN,0)
    }
    fun saveAnswer(str: String){
        editor.putString(ANSWER,str).apply()
    }
    fun getAnswer(): String?{
        return preferences.getString(ANSWER,"");
    }
    fun saveCorrectAnswerIndexes(indexes: List<Int>) {
        editor.putString(CORRECT_INDEXES, indexes.joinToString(","))
        editor.apply()
    }

    fun getCorrectAnswerIndexes(): List<Int> {
       val indexesStr = preferences.getString(CORRECT_INDEXES,"")
        return if (indexesStr.isNullOrEmpty()) {
            ArrayList()
        } else {
            indexesStr.split(",").map { it.toInt() }
        }
    }
    fun saveVariants(variants: String){
        editor.putString(SAVED_VARIANTS,variants).apply()
    }
    fun getVariant(): String?{
        return preferences.getString(SAVED_VARIANTS,"")
    }
    fun saveResume(res: Boolean){
        editor.putBoolean(RESUME,res).apply()
    }
    fun getResume(): Boolean{
       return preferences.getBoolean(RESUME,false)
    }
}