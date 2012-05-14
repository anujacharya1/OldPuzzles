using System;
using System.Threading;

namespace CircularQueue
{
	class CircularQueue
	{
		private int[] queue;
		private int head;
		private int tail;
		private int length;
		static Object lockThis = new Object ();

		public CircularQueue ()
		{
			Initialize ();
		}

		private void Initialize ()
		{
			head = tail = -1;

			Console.Write ("Enter Circular Queue size: ");

			string length = Console.ReadLine ();

			this.length = int.Parse (length);

			queue = new int[this.length];
		}

		private void enqueue (int value)
		{
			lock (lockThis) {
				if ((head == 0 && tail == length - 1) || (tail + 1 == head)) {
					Console.WriteLine ("Circular queue is FULL.");

					return;
				} else {
					if (tail == length - 1)
						tail = 0;
					else
						tail++;

					queue [tail] = value;

					Console.WriteLine ("enqueue -> {0}", value);
				}

				if (head == -1)
					head = 0;
			}
		}

		private void dequeue ()
		{
			lock (lockThis) {
				int value;
				if (head == -1) {
					Console.WriteLine ("Circular queue is EMPTY.");
					value = -1;
				} else {
					value = queue [head];
					queue [head] = 0;

					if (head == tail)
						head = tail = -1;
					else if (head == length - 1)
						head = 0;
					else
						head++;
				}
		
				Console.WriteLine ("dequeue -> {0}", value);
			}
		}

		private void Show ()
		{
			lock (lockThis) {
				int i;

				if (head == -1) {
					Console.WriteLine ("Circular queue is EMPTY.");
					return;
				} else {
					if (tail < head) {
						for (i = 0; i <= length - 1; i++)
							Console.Write ("{0} ", queue [i]);
					} else {
						for (i = 0; i <= tail; i++)
							Console.Write ("{0} ", queue [i]);
					}
					Console.WriteLine ();
				}
			}
		}

		public void EnqueueDequeue ()
		{
			enqueue (1);
			enqueue (2);
			Show ();
			enqueue (3); // Circular queue is full!
			dequeue ();
			dequeue ();
			dequeue ();
			dequeue ();
			dequeue (); // Circular queue is empty!
			dequeue (); // Circular queue is empty!
			Show ();
			enqueue (4);
			Show ();
			enqueue (5);
			Show ();
			dequeue ();
			dequeue ();
			enqueue (6);
			enqueue (7);
			Show (); 
		}
		
		static void Main (string[] args)
		{
			CircularQueue cq= new CircularQueue ();

			Thread[] threads = new Thread[10];

			for (int i = 0; i < threads.Length; i++) {
				threads [i] = new Thread (new ThreadStart (cq.EnqueueDequeue));
			}

			for (int i = 0; i < threads.Length; i++) {
				threads [i].Start ();
			}

			Console.ReadLine ();
		}
	}
}