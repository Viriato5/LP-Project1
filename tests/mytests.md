// L0 tests

let a = 5;
a;;

let a = true;
let b = true;
a && b;;

let a = true;
let b = false;
a || b;;

let a = 1;
let b = 2;
a == b;;

let a = 1;
let b = 2;
a != b;;

let a = 1;
let b = 2;
a >= b;;

let a = 1;
let b = 2;
a <= b;;

let a = 1;
let b = 2;
a < b;;

let a = 1;
let b = 2;
a > b;;

5+5;;

6-4;;

10*2;;

10/2;;

let a = 10;
-a;;

let a = true;
~a;;

let a = 0;
print(a+1);
println(a);;

let a = true;
if (a) {println(1)}
else {println(2)};;

let f = fn a => {a+2};
f (2);;

// L1

let a = box(0);
println(!a);
a := !a + 20;
!a;;

let a = box(10);
let f = fn a => {
    println(!a);
    a := !a - 1
};
while (!a > 0) {
    f (a)
};;
