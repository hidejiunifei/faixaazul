package hideji.hayakawa.faixaazul

import android.graphics.Color

class ConvertTimeToColor {
    companion object {
        fun convertTimeToColor(time: String) : Int{
            val color = when(time){
                "1 HORA(S)" -> Color.RED
                "2 HORA(S)" -> Color.BLUE
                "5 HORA(S)" -> Color.GREEN
                else -> Color.YELLOW
            }

            return color
        }
    }
}