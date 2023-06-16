package com.example.proton.ui.detailDiagramPredict

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proton.R
import com.example.proton.databinding.ActivityDetailDiagramPredictBinding
import com.example.proton.model.ProductModel
import com.example.proton.utils.DefaultFormat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


@Suppress("DEPRECATION")
class DetailDiagramPredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDiagramPredictBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDiagramPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24)
            title = ""
        }


        val product = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(DATA_PRODUCT, ProductModel::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(DATA_PRODUCT)
        }

        if(product != null){
            val kasus = ArrayList<Entry>()

            kasus.add(Entry(1F, product.predict.w1.toFloat()))
            kasus.add(Entry(2F, product.predict.w2.toFloat()))
            kasus.add(Entry(3F, product.predict.w3.toFloat()))
            kasus.add(Entry(4F, product.predict.w4.toFloat()))

            val kasusLineDataSet = LineDataSet(kasus, "Terjual")
            kasusLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            kasusLineDataSet.color = R.color.primary
            kasusLineDataSet.circleRadius = 5f
            kasusLineDataSet.setCircleColor(R.color.primary)

            val legend = binding.lineChart.legend
            legend.isEnabled = true
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.orientation = Legend.LegendOrientation.HORIZONTAL
            legend.setDrawInside(false)

            binding.lineChart.description.isEnabled = false
            binding.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            binding.lineChart.data = LineData(kasusLineDataSet)
            binding.lineChart.animateXY(100, 500)

            binding.valueName.text = product.name
            binding.valueDate.text = DefaultFormat.getFormattedDate()
            binding.valueStock.text = getString(R.string.value_stock, product.stock.toString())
            binding.valueWeek1.text = getString(R.string.value_stock, product.predict.w1.toString())
            binding.valueWeek2.text = getString(R.string.value_stock, product.predict.w2.toString())
            binding.valueWeek3.text = getString(R.string.value_stock, product.predict.w3.toString())
            binding.valueWeek4.text = getString(R.string.value_stock, product.predict.w4.toString())
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val DATA_PRODUCT = "data_product"
    }
}