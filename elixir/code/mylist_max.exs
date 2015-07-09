defmodule MyList do
  def greater(l, r) when l<r do
    r
  end
  def greater(l, r) when l>r do
    l
  end
  def greater(l, r) when l==r do
    r
  end

  def maxelm([head|tail]) do
    _max_val(tail, head)
  end

  defp _max_val([], value) do
    value
  end
  defp _max_val([head|tail], value) do
    _max_val(tail, greater(head, value))
  end
end

IO.puts MyList.maxelm([1, 3, 10, 4, 5, 10, 9])
