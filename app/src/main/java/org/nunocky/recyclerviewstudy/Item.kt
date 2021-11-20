package org.nunocky.recyclerviewstudy

import androidx.lifecycle.MutableLiveData

open class Item(
    open val id: Long,
    open val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other?.javaClass != javaClass) return false
        other as Item
        return this.id == other.id
    }

    // Android Studioが自動生成
    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        return result
    }
}

data class TextItem(
    override val id: Long,
    override val title: String,
    var text : MutableLiveData<String> = MutableLiveData("")
) : Item(id, title)

data class BooleanItem(
    override val id: Long,
    override val title: String,
    var checked: MutableLiveData<Boolean> = MutableLiveData(false)
) : Item(id, title)
