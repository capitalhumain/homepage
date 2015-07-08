defmodule MyList do
  def greater(l, r) when l<r do
    r
  end
  def greater(l, r) when l>r do
    l
  end
  
  def max_val([head|tail]) do
    _max_val(tail, head)
  end

  defp _max_val([], value) do
    value
  end
  defp _max_val([head|tail], value) do
    _max_val(tail, greater(head, value))
  end
end

IO.puts MyList.max_val([1, 3, 4, 5, 10, 9])
