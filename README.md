[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)

This is a Java implementation of the Huffman Tree Compression Algorithm.

The algorithm works by taking the original text from 'data.txt' and converts the raw text into a binary tree. The binary tree is used to compress and then decompress the raw text given. Construction of the Code Table is crucial to the reconstruction of the text, as the user will need the Code Table in order to 'De-Hash' all of the compressed characters. Each pathway to a given leaf is its "Encryption Value" and since it is a binary tree, there is only one way to get to each leaf node. On average, the Huffman Tree saves about 66.66% of bits in compression.

Enjoy
