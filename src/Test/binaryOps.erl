% Huy Huynh

-module(binaryOps).
-export([binNegate/1, binAnd/2, binOr/2, binXor/2, binAdd/2, trimLeading/1]).


% Question 1.
binNegate(BinL) -> 
    Temp = negate(BinL),
    trimZeros(Temp).

negate([]) -> [];
negate([H | T]) when H == 0 -> [1 | negate(T)];
negate([H | T]) when H == 1 -> [0 | negate(T)].

trimZeros([]) -> [];
trimZeros([0 | T]) -> trimZeros(T);
trimZeros(BinL) -> BinL.  



% Question 2.
binAnd([], []) -> [];
binAnd(BinL1, BinL2) when length(BinL1) < length(BinL2) ->
    NewBinL1 = fillZeros(BinL1, length(BinL2) - length(BinL1)),
    Answer = performAnd(NewBinL1, BinL2),
    trimZeros(Answer);
binAnd(BinL1, BinL2) when length(BinL1) > length(BinL2) ->
    NewBinL2 = fillZeros(BinL2, length(BinL1) - length(BinL2)),
    Answer = performAnd(BinL1, NewBinL2),
    trimZeros(Answer);
binAnd(BinL1, BinL2) ->
    Answer = performAnd(BinL1, BinL2),
    trimZeros(Answer).

fillZeros(L, Length) -> 
    ZerosList = generateZeros(Length, 0, []),
    ZerosList ++ L.

generateZeros(Length, Length, List) -> List;
generateZeros(Length, Count, List) -> [0 | generateZeros(Length, Count + 1, List)].

performAnd([], []) -> [];
performAnd([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 1 -> [1 | performAnd(T1, T2)];
performAnd([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 0 -> [0 | performAnd(T1, T2)];
performAnd([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 1 -> [0 | performAnd(T1, T2)];
performAnd([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 0 -> [0 | performAnd(T1, T2)].

% Question 3.
binOr([], []) -> [];
binOr(BinL1, BinL2) when length(BinL1) < length(BinL2) ->
    NewBinL1 = fillZeros(BinL1, length(BinL2) - length(BinL1)),
    Answer = performAnd(NewBinL1, BinL2),
    trimZeros(Answer);
binOr(BinL1, BinL2) when length(BinL1) > length(BinL2) ->
    NewBinL2 = fillZeros(BinL2, length(BinL1) - length(BinL2)),
    Answer = performOr(BinL1, NewBinL2),
    trimZeros(Answer);
binOr(BinL1, BinL2) ->
    Answer = performOr(BinL1, BinL2),
    trimZeros(Answer).

performOr([], []) -> [];
performOr([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 1 -> [1 | performOr(T1, T2)];
performOr([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 0 -> [1| performOr(T1, T2)];
performOr([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 1 -> [1 | performOr(T1, T2)];
performOr([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 0 -> [0 | performOr(T1, T2)].

% Question 4.
binXor([], []) -> [];
binXor(BinL1, BinL2) when length(BinL1) < length(BinL2) ->
    NewBinL1 = fillZeros(BinL1, length(BinL2) - length(BinL1)),
    Answer = performAnd(NewBinL1, BinL2),
    trimZeros(Answer);
binXor(BinL1, BinL2) when length(BinL1) > length(BinL2) ->
    NewBinL2 = fillZeros(BinL2, length(BinL1) - length(BinL2)),
    Answer = performXor(BinL1, NewBinL2),
    trimZeros(Answer);
binXor(BinL1, BinL2) ->
    Answer = performXor(BinL1, BinL2),
    trimZeros(Answer).

performXor([], []) -> [];
performXor([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 1 -> [0 | performXor(T1, T2)];
performXor([H1 | T1], [H2 | T2]) when H1 == 1, H2 == 0 -> [1| performXor(T1, T2)];
performXor([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 1 -> [1 | performXor(T1, T2)];
performXor([H1 | T1], [H2 | T2]) when H1 == 0, H2 == 0 -> [0 | performXor(T1, T2)].

% Question 5.
binAdd([], []) -> [];
binAdd(BinL1, BinL2) when length(BinL1) < length(BinL2) ->
    NewBinL1 = fillZeros(BinL1, length(BinL2) - length(BinL1)),
    Answer = performAdd(lists:reverse(NewBinL1), lists:reverse(BinL2), 0),
    trimZeros(lists:reverse(Answer));
binAdd(BinL1, BinL2) when length(BinL1) > length(BinL2) ->
    NewBinL2 = fillZeros(BinL2, length(BinL1) - length(BinL2)),
    Answer = performAdd(lists:reverse(BinL1), lists:reverse(NewBinL2), 0),
    trimZeros(lists:reverse(Answer));
binAdd(BinL1, BinL2) ->
    Answer = performAdd(lists:reverse(BinL1), lists:reverse(BinL2), 0),
    trimZeros(lists:reverse(Answer)).

performAdd([], [], 0) -> [];
performAdd([], [], Carry) -> [Carry | []];
performAdd([H1 | T1], [H2 | T2], Carry) ->
    Sum = H1 + H2 + Carry,
    ResultBit = Sum rem 2,
    NewCarry = Sum div 2,
    [ResultBit | performAdd(T1, T2, NewCarry)].

% Question 6.

trimLeading([]) -> [];
trimLeading(BinL) -> trimZeros(BinL).