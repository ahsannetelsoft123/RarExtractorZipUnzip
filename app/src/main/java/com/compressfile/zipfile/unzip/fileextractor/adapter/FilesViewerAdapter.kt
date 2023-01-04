package com.compressfile.zipfile.unzip.fileextractor.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.compressfile.zipfile.unzip.fileextractor.R
import com.compressfile.zipfile.unzip.fileextractor.databinding.FileItemLayoutBinding
import com.compressfile.zipfile.unzip.fileextractor.model.FileItem
import com.compressfile.zipfile.unzip.fileextractor.utils.openFile

@SuppressLint("NotifyDataSetChanged")
class FilesViewerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list= arrayListOf<FileItem>()
    var isMultipleSelection=true
    var fileEvents:FileEvents?=null

    interface FileEvents{
        fun onSelectItem(count: Int)
    }

    fun multipleSelection(multipleSelection: Boolean){
        this.isMultipleSelection =multipleSelection
        notifyDataSetChanged()
    }

    fun setList(arrayList: ArrayList<FileItem>){
        list=arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding=FileItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        val item=list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: FileItemLayoutBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: FileItem){
            binding.fileName.text=item.name
            Glide.with(itemView.context)
                .load(item.path)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView)
            binding.checkBox.isChecked=item.isSelected
            if (isMultipleSelection) binding.checkBox.visibility=View.VISIBLE else binding.checkBox.visibility=View.GONE
        }

        init {
            itemView.setOnClickListener {
                val item=list[adapterPosition]
                if (isMultipleSelection){
                    binding.checkBox.isChecked=!binding.checkBox.isChecked
                    item.isSelected=binding.checkBox.isChecked
                    fileEvents?.onSelectItem(getMultipleSelected().size)
                }else{
                    itemView.context.openFile(item)
                }
            }
        }
    }

    fun getMultipleSelected():List<FileItem>{
        return list.filter { it.isSelected }
    }



}