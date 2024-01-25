# SE450_JPaint_Project
JPaint Application simulating the features of Windows-based Paint application. Technologies Used - Java 8 | AWT Swings. This application has been developed as a part of my final project for SE 450 course - Object-Oriented Software Development at DePaul under the guidance and supervision of Prof. Jeffrey Sharpe. I got an opportunity to apply SOLID principles and 5 Software Design Patterns in this project that I have learnt throughout the course. The starter code - mainly consist of GUI was provided by the Prof. Minimum GUI code have been written by me. Java API's have been called for drawing the shapes and event handling's have been written for click and drag events. This project has been developed in an Agile approach. Each Sprint lasted for 2 weeks. 

Features by Sprint: 

Sprint 1: Draw a filled-in Rectangle : When mouse is click-and-dragged while in Draw mode, a Rectangle will display on the screen. The Rectangle will match the direction and size of the mouse movement.

Sprint 2: Draw Rectangles, Ellipses, and Triangles. 
          Draw shapes with various colors
          Draw shapes with various shading types : 
            Outline Only – Only shape outline will be drawn. Use Primary Color to draw this. 
            Filled-In – Only the inside of the shape will be drawn – there will be no visible outline. Use Primary Color to draw this.
            Outline and Filled-In – Both the inside and the outline will be drawn. Use Primary Color for the inside and Secondary Color for the outline.
          Outline Selected Shapes
          

![sprint1_UKQAyzdG](https://user-images.githubusercontent.com/19336011/112191527-1a917500-8bd4-11eb-8a0b-5980b7606b7d.gif)

Sprint 2: Select a shape. In Select mouse mode, select any shapes that are touched by the invisible bounding box created by clicking and dragging to select.
          If you click a single point on the canvas or on a shape while in Select mode, that shape should be selected. 
          This is the default behavior for collision detection – this is easier for you! At this point, nothing visible has to happen.


![selectshape1_OzeYGSMo](https://user-images.githubusercontent.com/19336011/112184412-2f1e3f00-8bcd-11eb-96d5-4f8a76c5ac16.gif)

Sprint 2: Move a shape. In Move Mouse Mode, clicking and dragging will offset any Selected shapes by the amount your mouse moves.

![sprint3_chotQ3Z2](https://user-images.githubusercontent.com/19336011/112174359-59b7ca00-8bc4-11eb-9303-0a2f3e70b87b.gif)

Sprint 3: Copy Shapes, Paste Shapes, Delete Shapes

Sprint 4: - Undo : Undo the last operation. The following need to be Undoable: Draw, Move, Paste, Delete
            Redo : Redo the last operation. See undo.

![sprint4_LfUGsIpc](https://user-images.githubusercontent.com/19336011/112194982-6e518d80-8bd7-11eb-88c6-2657bd194fec.gif)
