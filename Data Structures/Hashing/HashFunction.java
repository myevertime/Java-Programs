public interface HashFunction<T>
{
    int hash(T data, int current_table_length);
}
