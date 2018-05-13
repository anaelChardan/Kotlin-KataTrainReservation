package Unit.Domain.Builder

interface Builder<T> {
    fun build(): T
}