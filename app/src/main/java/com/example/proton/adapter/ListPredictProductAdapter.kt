package com.example.proton.adapter

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proton.R
import com.example.proton.data.remote.response.DataItem
import com.example.proton.model.PredictionResult
import com.example.proton.model.ProductModel
import com.example.proton.ui.detailDiagramPredict.DetailDiagramPredictActivity
import com.example.proton.ui.managementDetailProduct.ManagementDetailProductActivity
import com.example.proton.utils.DefaultFormat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ListPredictProductAdapter(private val listData: List<ProductModel>) : RecyclerView.Adapter<ListPredictProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_predict_product, viewGroup, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.name.text = data.name
        holder.stock.text = data.stock.toString()
        holder.date.text = DefaultFormat.getFormattedDate()
        val kasus = ArrayList<Entry>()

        kasus.add(Entry(1F, data.predict.w1.toFloat()))
        kasus.add(Entry(2F, data.predict.w2.toFloat()))
        kasus.add(Entry(3F, data.predict.w3.toFloat()))
        kasus.add(Entry(4F, data.predict.w4.toFloat()))

        val kasusLineDataSet = LineDataSet(kasus, "Terjual")
        kasusLineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        kasusLineDataSet.color = R.color.primary
        kasusLineDataSet.circleRadius = 5f
        kasusLineDataSet.setCircleColor(R.color.primary)

        val legend = holder.lineChart.legend
        legend.isEnabled = true
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER)
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        legend.setDrawInside(false)

        holder.lineChart.description.isEnabled = false
        holder.lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        holder.lineChart.data = LineData(kasusLineDataSet)
        holder.lineChart.animateXY(100, 500)




        holder.itemView.setOnClickListener {
            val intentDetail =Intent(holder.itemView.context, DetailDiagramPredictActivity::class.java)
            intentDetail.putExtra(DetailDiagramPredictActivity.DATA_PRODUCT, data)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount() = listData.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.product_name)
        val stock: TextView = itemView.findViewById(R.id.valueStock)
        val date: TextView = itemView.findViewById(R.id.valueDate)
        val lineChart: LineChart = itemView.findViewById(R.id.lineChart1)
    }
}
