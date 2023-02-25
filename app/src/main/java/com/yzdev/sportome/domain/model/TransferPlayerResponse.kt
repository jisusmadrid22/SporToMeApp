package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.player.TransferPlayerDto

data class TransferPlayerResponse(
    val response: List<Response>,
) {
    data class Response(
        val player: Player,
        val transfers: List<Transfer>,
        val update: String
    ) {
        data class Player(
            val id: Int,
            val name: String
        )

        data class Transfer(
            val date: String,
            val teams: Teams,
            val type: String
        ) {
            data class Teams(
                val inTeam: In,
                val outTeam: Out
            ) {
                data class In(
                    val id: Int,
                    val logo: String,
                    val name: String
                )

                data class Out(
                    val id: Int,
                    val logo: String,
                    val name: String
                )
            }
        }
    }
}

/** mapper*/
fun TransferPlayerDto.toTransferPlayerLocal(): TransferPlayerResponse{
    return TransferPlayerResponse(
        response = response.toTransferPlayerLocalResponse()
    )
}

private fun List<TransferPlayerDto.Response>.toTransferPlayerLocalResponse(): List<TransferPlayerResponse.Response>{
    return this.map {
        TransferPlayerResponse.Response(
            player = it.player.toTransferPlayerLocalResponsePlayer(),
            transfers = it.transfers.toTransferPlayerLocalResponseTransfer(),
            update = it.update
        )
    }
}

private fun TransferPlayerDto.Response.Player.toTransferPlayerLocalResponsePlayer(): TransferPlayerResponse.Response.Player{
    return TransferPlayerResponse.Response.Player(
        id, name
    )
}

private fun List<TransferPlayerDto.Response.Transfer>.toTransferPlayerLocalResponseTransfer(): List<TransferPlayerResponse.Response.Transfer>{
    return this.map {
        TransferPlayerResponse.Response.Transfer(
            date = it.date,
            teams = it.teams.toTransferPlayerLocalResponseTransferTeam(),
            type = it.type
        )
    }
}

private fun TransferPlayerDto.Response.Transfer.Teams.toTransferPlayerLocalResponseTransferTeam(): TransferPlayerResponse.Response.Transfer.Teams{
    return TransferPlayerResponse.Response.Transfer.Teams(
        inTeam = inTeam.toTransferPlayerLocalResponseTransferTeamIn(),
        outTeam = outTeam.toTransferPlayerLocalResponseTransferTeamOut()
    )
}

private fun TransferPlayerDto.Response.Transfer.Teams.In.toTransferPlayerLocalResponseTransferTeamIn(): TransferPlayerResponse.Response.Transfer.Teams.In{
    return TransferPlayerResponse.Response.Transfer.Teams.In(
        id, logo, name
    )
}

private fun TransferPlayerDto.Response.Transfer.Teams.Out.toTransferPlayerLocalResponseTransferTeamOut(): TransferPlayerResponse.Response.Transfer.Teams.Out{
    return TransferPlayerResponse.Response.Transfer.Teams.Out(
        id, logo, name
    )
}