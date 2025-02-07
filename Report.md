# What is multithreading

Multithreading is the ability in a CPU to provide multiple threads of executions,
in other words, how many tasks a CPU can handle at a time.
In theory, if you use multiple threads of a CPU, you will distribute the load of an operation across multiple threads,
which will solve their assigned tasks. This will lead to an overall performance improvement,
as the CPU will be able to handle work on several tasks at the same time.

# Time difference

Through the earlier explanation, we can surmise that multithreading will perform better than single-threading.
Unlike the theory, the results of the test show that the single-threading is faster than multithreading.
The reason for this might be that Thread creation and management are expensive operations,
and as a result, the time taken to create and manage threads is greater than the time saved by the threads themselves.
In theory, multithreading would allow ten clients to ask a server, and it would respond to all of them at the same time,
while single-threading would respond to one client at a time.
Our tests were run on localhost, so the test might have been different if we had run it on a separate server/machine
due to the delay it would take to send and receive the data back.

During these tests, we also added a sleep function to simulate a delay in the server response.
During the time when we added this delay, the multithreading was faster than the single-threading.

# Test outcomes

With no delay and 10 clients:

- Single-threading: 0–1 ms
- Multithreading: 0–2 ms
- Difference: 0–1 ms
- The single-threading was faster than the multithreading.

With a delay of 10 ms and 10 clients:

- Single-threading: 4 ms
- Multithreading: 0-1 ms
- Difference: 3-4 ms
- The multithreading was faster than the single-threading.

With a delay of no delay and 40 clients:

- Single-threading: 0-1 ms
- Multithreading: 5-6 ms
- Difference: 4-5 ms
- The single-threading was faster than the multithreading.

With a delay of 10 ms and 40 clients:

- Single-threading: 6-7 ms
- Multithreading: 6 ms
- Difference: 0-1 ms
- The multithreading was faster than the single-threading.

# Conclusion

To get realistic results on how multithreading performs,
we need to test it on a separate server/machine.
Due to the simplicity of the calculations,
the single-threading was faster than the multithreading.
The multithreading was faster than the single-threading when
we added a delay to the server response.