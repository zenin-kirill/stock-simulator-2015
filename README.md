# Cимулятор склада (Stock simulator)

Программа - унверситетская задачка на языке Java с графическим интерфейсом, моделирующая работу склада с тремя типами продуктов. С помощью многопоточной реализации воссоздана параллельная работа множества исполнителей, которыми управляет диспетчер.  
  
Склад имеет 3 помещения под каждый вид продуктов. На склад приезжают поезда, которые имеют вагоны с различными продуктами, вагоны разгружаются грузчиками. Запасы на складе в разных помещениях при этом растут.  
  
С другой стороны склада по-очереди подъезжают грузовики трех типов. В них другие грузчики загружают продукцию со склада.  
  
Диспетчер контролирует транспорт и работу грузчиков. Иногда случается так, что часть склада оказывается переполненной или пустой.

Всё это работает параллельно в режиме реального времени. У каждого свой трэд: у отрисовщика окна, у диспетчера склада, у каждого грузчика. Для того, чтобы все эти сущности работали дружно с общими ресурсами, сделана соотвествующая блокировка ресурсов.
  
  Команды компиляции и запуск:  
  ```
  javac MainClass.java  
  java MainClass  
  ```
  ![image](/4.png)
  
  MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
