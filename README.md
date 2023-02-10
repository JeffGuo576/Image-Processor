![solarized palette](https://github.com/JeffGuo576/Image-Processor/blob/main/GUIScreenShot.png)

Click on:
- the load image button and select the image file to display on the GUI and there will be a pop up
after selecting the image that takes in your input of the file name you would like the program
to refer the image as.
- the same file allows you to save the current image that is being displayed on the GUI. You just
need to give it a name and the type of file you want to save it as. e.g.
- any of the operations you would like to apply to the displayed image, there will be a pop up
where you input the file name of the image you want to edit and the new file name for the
edited image.
- a histogram of the displayed image will automatically refresh and register

HW5 Changes:
We added support for image formats and new features such as blur, sharpen, sepia-tone and greyscale for image format photos.
The controller, view and model should be complete. We added new methods to provide support for the new image formats. Initially our load method only worked for PPM files. We decided to make our load method work for either ppm files or image format files to create flexibility.
Once we created the flexibility, we just added methods that support the features for the new image formats such as blur, sharpen, sepia tone etc.
Added IPair interface
Added Pair class


HW6 Changes:
We added support for histogram that allows you to see the frequencies and values of the image. Also, addition of the GUI for the image processor.
The controller, view and model should be complete. We added new interfaces and classes that focused on the GUI and Histogram.
Added GUIController interface
Added GUIControllerImpl class
Added GUI interface
Added GUIView class

List of interfaces/classes:

DrawHistogram class:
Paints the histogram rectangle bars for all the components.

GUIController interface:
Represents a GUI controller that receives and sends data to the GUI view and image model.

GUIControllerImpl class:
Represents a GUI controller that works with the image model and the GUI view.

GUI interface:
Represents a GUI. Displays the image processor to the client with functioning buttons for commands.

GUIView class:
Represents a GUI view that displays image being edited and buttons for the commands.

IPair interface:
IPair represents a pair. A pair holds the row and column of a pixel (or the position).

Pair class:
Represents a pair of numbers; row and column which represents the position of a pixel.

ImageModel interface:
Represents an Image model where all implementations of this model has the functionalities of saving, loading, vertically flip, horizontally flip, apply components, and brighten an image. 

ImageModelImpl class:
Represents an Image model implementation. This allows the image processor to perform specific commands to an image. There is one constructor which does not take any arguments. It initializes the field images to a hashmap. The key is the client's desired file/image name and the value is the image in a form of a 2D array of pixels.

Pixel class:
Represents a Pixel. Red, green, blue values (RGB) makes a pixel along with its color max value. The class provides setters and getters for its fields. There is also an equals method along with a hashCode method to so tests can work when comparing list of pixels or just pixels.

ImageController interface:
Represents an image controller that reads the commands from the client either from the console or any other readbles such as a file. An image controller contains a go method that starts the image processor that we encoded.

ImageControllerImpl class:
Represents an image controller implementation. It reads commands and decides which method should be called from the model and sent to the view to render a message.

ImageView interface:
Represents an image view. An image view renders messages to the client depending on the desired appendable. It recieves the messages to render
from the controller.

ImageTextView class:
Represents an image text view or implementation of an image view. It contains two constructors and a renderMessage method. One constructor only takes in a model and sets the appendable field to System.in by default. The second constructor takes in both model and desired appendable.

Instructions:
To edit an image you must write out commands. All commands require the name of command first followed by a file name and the path or destination of a file. Order matters. The code will not break if typed command is not in the right order, but it will notify the client to input the right command or the the file name cannot be found. There are in total of 12 commands: load, save, applying components (6 of them), horizontally flip, veritcally flip, and brightening an image.

Commands:

load file-name path-name
save file-name path-name
red-component file-name destination-image-name
green-component file-name destination-image-name
blue-component file-name destination-image-name
value-component file-name destination-image-name
intensity-component file-name destination-image-name
luma-component file-name destination-image-name
horizontal-flip file-name destination-image-name
vertical-flip file-name destination-image-name
blur file-name destination-image-name
greyscale file-name destination-image-name
sharpen file-name destination-image-name
sepiaTone file-name destination-image-name

To brighten an image increment-number must be positive.
To darken an image increment-number must be negative.

brighten file-name destination-image-name increment-number

Script of commands:
// load koala.ppm and call it "koala"
load koala koala.ppm

// flip koala vertically
vertical-flip koala koala-vertical-flip

// save koala-vertical-flip
save koala-vertical-flip koala-vertical.ppm

// load michael-jordan.ppm and call it "MJ"
load MJ michael-jordan.ppm

// apply red-component to MJ
red-component MJ MJ-red-component

// horizontally flip MJ-red-component
horizontal-flip MJ-red-component MJ-Horizontal-red

// save MJ-Horizontal-red
save MJ-Horizontal-red MJ-Horizontal-Red.ppm

// brighten koala-vertical-flip by 50
brighten koala-vertical-flip koala-vertical-brighten-by-50 50

// dark koala by 30
brighten koala koala-darken-by-30 -30

// save koala-vertical-brighten-by-50
save koala-vertical-brighten-by-50 koala-vertical-brighten-by-50.ppm

// save koala-darken-by-30
save koala-darken-by-30 koala-darken-by-30.ppm

// overwrite koala with another file
load koala michael-jordan.ppm

// to stop the program
quit

Citation:
Michael Jordan photograph
Photo: © Ralf-Finn Hestoft/CORBIS/Corbis via Getty Images
example5000 photograph is an image we made and are authorizing the use of it in the project
