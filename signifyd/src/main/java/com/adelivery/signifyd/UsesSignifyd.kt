package com.adelivery.signifyd

import android.content.Context
import android.util.Log
import com.threatmetrix.TrustDefender.TMXProfiling
import com.threatmetrix.TrustDefender.TMXConfig

class UsesSignifyd(private val context: Context){

    init {
        initSignifyd()
    }

    fun initSignifyd() {
        try {
            // Inicialização da instância do perfil
            val profile = TMXProfiling.getInstance()

            // Chaves definidas para o projeto (ver documentação)
            val orgID = "w2txo5aa"
            val fingerprintServer = "imgs.signifyd.com"
            Log.d("UsesSignifyd", "initSignifyd: TMXProfiling.getInstance")

            // Necessária a configuração antes de utilizar
            val tmxConfig = TMXConfig()
            tmxConfig.setContext(context)
            tmxConfig.setOrgId(orgID)
            tmxConfig.setFPServer(fingerprintServer)
            profile.init(tmxConfig)

            // Realização da request para pegar o ID da sessão para este device
            TMXProfiling.getInstance().profile { result ->
                // Armazena no SharedPreferences para uso posterior no Checkout
                Log.d("UsesSignifyd", "TMXProfiling.getInstance().profile: ${result.sessionID}")
//                PrefsHelper.write(key = PrefsHelper.SESSION_ID, value = result.sessionID)
            }
        } catch (e: Exception) {
            Log.d("UsesSignifyd", "catch error: $e")
            e.printStackTrace()
        }
    }
}
