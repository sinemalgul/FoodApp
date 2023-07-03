package com.sinemalgul.foodapp.ui.chatbot

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..1).random()
        val message = _message.lowercase()

        return when {
            message.contains("merhaba") -> {
                when (random) {
                    0 -> "Merhaba! Hoşgeldin :) "
                    1 -> "Merhaba! Foodies'e hos geldin. "
                    else -> "error"
                }
            }

            message.contains("siparis vermek istiyorum")  -> {
                when (random) {
                    0 -> "Siparis vermek icin ana sayfada bulunan menuyu kullanabilir veya Siparis Ver butonuna tiklayabilirsiniz. Ardindan istediginiz yemegi secip, teslimat adresinizi ve odeme yontemini belirlemeniz gerekmektedir."
                    1 -> "Siparis vermek icin populer restoranlardan veya arama kismindan istediginiz restoranti secip yemeginizi siparis verebilirsiniz."
                    else -> "error"
                }
            }

            message.contains("siparisim ne zaman teslim edilecek") -> {
                when (random) {
                    0 -> "Teslimat suresi siparis verilen yemege ve bulundugunuz konuma bagli olarak degisebilir."
                    1 -> "Siparisiniz hazirlaniyor. Lutfen bekleyiniz."
                    else -> "error"
                }
            }

            message.contains("yardim almak istiyorum")  -> {
                when (random) {
                    0 -> "Tabii, size nasil yardimci olabilirim? "
                    1 -> "Elbette! Hangi konuda yardima ihtiyaciniz var?"
                    else -> "error"
                }
            }
            else -> {
                when (random) {
                    0 -> "Sorunu anlayamadim. Tekrar edebilir misin?"
                    1 -> "Lütfen tekrar sorabilir misin?"
                    else -> "error"
                }
            }
        }
    }
}