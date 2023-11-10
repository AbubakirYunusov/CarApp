package com.example.carapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carapp.databinding.CarItemBinding

class CarAdapter(
    private val onClickDeleteBtn: (index: Int) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    private val carList = mutableListOf<CarModels>()

    fun updateList(carList: List<CarModels>) {
        this.carList.clear()
        this.carList.addAll(carList)
        notifyDataSetChanged()
    }

    inner class CarViewHolder(
        private val binding: CarItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(carModels: CarModels) {
            binding.tvMarc.text = carModels.mark
            binding.tvModels.text = carModels.model
            binding.deleteButt.setOnClickListener {
                onClickDeleteBtn.invoke(
                    carList.indexOf(carModels)
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CarViewHolder {
        val binding = CarItemBinding.bind(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.car_item, parent, false)
        )
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int,
    ) {
        holder.bind(carList[position])
    }
}