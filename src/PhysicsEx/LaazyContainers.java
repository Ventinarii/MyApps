package PhysicsEx;

import java.util.Objects;

public class LaazyContainers {
    //*always there when you need it and can't use C# anonymous classes*
    public static class C8<A,B,C,D,E,F,G,H> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public F f;//6
        public G g;//7
        public H h;//8
        public C8(A a, B b, C c, D d, E e, F f, G g, H h){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
            this.g=g;
            this.h=h;
        }
        public C8(A a, B b, C c, D d, E e, F f, G g){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
            this.g=g;
        }
        public C8(A a, B b, C c, D d, E e, F f){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
        }
        public C8(A a, B b, C c, D d, E e){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
        }
        public C8(A a, B b, C c, D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public C8(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C8(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C8(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C8{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    ", g=" + g +
                    ", h=" + h +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C8<?, ?, ?, ?, ?, ?, ?, ?> c8 = (C8<?, ?, ?, ?, ?, ?, ?, ?>) o;
            return Objects.equals(a, c8.a) && Objects.equals(b, c8.b) && Objects.equals(c, c8.c) && Objects.equals(d, c8.d) && Objects.equals(e, c8.e) && Objects.equals(f, c8.f) && Objects.equals(g, c8.g) && Objects.equals(h, c8.h);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e, f, g, h);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C7<A,B,C,D,E,F,G> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public F f;//6
        public G g;//7
        public C7(A a, B b, C c, D d, E e, F f, G g){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
            this.g=g;
        }
        public C7(A a, B b, C c, D d, E e, F f){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
        }
        public C7(A a, B b, C c, D d, E e){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
        }
        public C7(A a, B b, C c, D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public C7(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C7(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C7(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C7{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    ", g=" + g +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C7<?, ?, ?, ?, ?, ?, ?> c7 = (C7<?, ?, ?, ?, ?, ?, ?>) o;
            return Objects.equals(a, c7.a) && Objects.equals(b, c7.b) && Objects.equals(c, c7.c) && Objects.equals(d, c7.d) && Objects.equals(e, c7.e) && Objects.equals(f, c7.f) && Objects.equals(g, c7.g);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e, f, g);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C6<A,B,C,D,E,F> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public F f;//6
        public C6(A a, B b, C c, D d, E e, F f){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
            this.f=f;
        }
        public C6(A a, B b, C c, D d, E e){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
        }
        public C6(A a, B b, C c, D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public C6(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C6(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C6(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C6{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    ", f=" + f +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C6<?, ?, ?, ?, ?, ?> c6 = (C6<?, ?, ?, ?, ?, ?>) o;
            return Objects.equals(a, c6.a) && Objects.equals(b, c6.b) && Objects.equals(c, c6.c) && Objects.equals(d, c6.d) && Objects.equals(e, c6.e) && Objects.equals(f, c6.f);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e, f);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C5<A,B,C,D,E> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public E e;//5
        public C5(A a, B b, C c, D d, E e){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
            this.e=e;
        }
        public C5(A a, B b, C c, D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public C5(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C5(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C5(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C5{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    ", e=" + e +
                    '}';
        }
        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C5<?, ?, ?, ?, ?> c5 = (C5<?, ?, ?, ?, ?>) o;
            return Objects.equals(a, c5.a) && Objects.equals(b, c5.b) && Objects.equals(c, c5.c) && Objects.equals(d, c5.d) && Objects.equals(e, c5.e);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C4<A,B,C,D,E> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public D d;//4
        public C4(A a, B b, C c, D d){
            this.a=a;
            this.b=b;
            this.c=c;
            this.d=d;
        }
        public C4(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C4(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C4(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C4{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    ", d=" + d +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C4<?, ?, ?, ?, ?> c4 = (C4<?, ?, ?, ?, ?>) o;
            return Objects.equals(a, c4.a) && Objects.equals(b, c4.b) && Objects.equals(c, c4.c) && Objects.equals(d, c4.d);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C3<A,B,C> implements Comparable{
        public A a;//1
        public B b;//2
        public C c;//3
        public C3(A a, B b, C c){
            this.a=a;
            this.b=b;
            this.c=c;
        }
        public C3(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C3(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C3{" +
                    "a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C3<?, ?, ?> c3 = (C3<?, ?, ?>) o;
            return Objects.equals(a, c3.a) && Objects.equals(b, c3.b) && Objects.equals(c, c3.c);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C2<A,B> implements Comparable{
        public A a;//1
        public B b;//2
        public C2(A a, B b){
            this.a=a;
            this.b=b;
        }
        public C2(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C2{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C2<?, ?> c2 = (C2<?, ?>) o;
            return Objects.equals(a, c2.a) && Objects.equals(b, c2.b);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    public static class C1<A> implements Comparable{
        public A a;//1
        public C1(A a){
            this.a=a;
        }
        @Override
        public String toString() {
            return "C1{" +
                    "a=" + a +
                    '}';
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            C1<?> c1 = (C1<?>) o;
            return Objects.equals(a, c1.a);
        }
        @Override
        public int hashCode() {
            return Objects.hash(a);
        }
        @Override
        public int compareTo(Object o) {
            return Integer.compare(o.hashCode(),hashCode());
        }
    }
    /*that was a lot of copy paste.
    * */
}
