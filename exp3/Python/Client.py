# Python program to implement client side of chat room.
import socket
import select
import sys

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
if len(sys.argv) != 3:
    print("Correct usage: script, IP address, port number")
    exit()
IP_address = '192.168.0.104'
Port = 6789
server.connect((IP_address, Port))

while True:

    # maintains a list of possible input streams
    input_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    input_socket.connect((IP_address, Port))
    sockets_list = [server, input_socket]

    #sockets_list = [sys.stdin, server]

    """ There are two possible input situations. Either the
	user wants to give manual input to send to other people,
	or the server is sending a message to be printed on the
	screen. Select returns from sockets_list, the stream that
	is reader for input. So for example, if the server wants
	to send a message, then the if condition will hold true
	below.If the user wants to send a message, the else
	condition will evaluate as true"""
    read_sockets, write_socket, error_socket = select.select(
        sockets_list, [], [])

    for socks in read_sockets:
        if socks == server:
            message = socks.recv(2048)
            print(message)
        else:
            message = sys.stdin.readline()
            input_socket.send(message.encode())
            sys.stdout.write("<You>")
            sys.stdout.write(message)
            sys.stdout.flush()

server.close()
