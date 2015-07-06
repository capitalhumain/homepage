defmodule Utils do
  def sum([]), do: 0
  def sum([head|tail]), do: head + sum(tail)

  def len([]), do: 0
  def len([head|tail]), do: 1 + len(tail)
end

IO.puts Utils.sum([])
IO.puts Utils.sum([1, 2, 3, 4, 5, 6, 7, 8, 9])

IO.puts Utils.len([])
IO.puts Utils.len([1, 2, 3, 4, 5, 6, 7, 8, 9])

