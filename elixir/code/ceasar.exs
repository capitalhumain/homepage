defmodule MyList do
  def mask(h) when h > 122 do
    '?'
  end
  def mask(h) when h <= 122 do
    h
  end

  def ceasar_m([], _n) do
    []
  end

  def ceasar_m([head|tail], n) do
    [ mask(head+n) | ceasar_m(tail, n)]
  end

  def ceasar([], _n) do
    []
  end

  def ceasar([head|tail], n) do
    [ 97+rem((head+n-97), 26) | ceasar(tail, n)]
  end
end

IO.puts MyList.ceasar('aztest', 1)
IO.puts MyList.ceasar_m('ztest', 10)
