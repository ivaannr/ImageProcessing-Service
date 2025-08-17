<h1 id="image-microservice-api">Image Processing API</h1>
<p>A lightweight microservice for storing and serving image files with database persistence. Built with <strong>Java</strong>, <strong>Spring Boot</strong>, and <strong>SQLite</strong>.</p>
<hr />
<h2 id="features">Features</h2>
<ul>
<li>Upload, download, and manage images.</li>
<li>Stores images in a database.</li>
<li>Configurable CORS for frontend integration.</li>
<li>Automatically generates database tables.</li>
<li>Users can interact with the database using the H2 console, to use it run the app and open <code>http://localhost:{port}/h2-console</code> in your browser.</li>
</ul>
<hr />
<h2 id="getting-started">Getting Started</h2>
<h3 id="prerequisites">Prerequisites</h3>
<ul>
<li>Java</li>
<li>Maven</li>
</ul>
<h3 id="installation">Installation</h3>
<ol>
<li>Clone the repository:</li>
</ol>
<pre><code class="language-bash">git clone https://github.com/ivaannr/ImageProcessing-Service.git
cd ImageProcessing-Service
</code></pre>
<ol start="2">
<li>Build the project:</li>
</ol>
<pre><code class="language-bash">mvn clean install
</code></pre>
<ol start="3">
<li>Run the application:</li>
</ol>
<pre><code class="language-bash">java -jar target/ImageMicroservice-1.0.0.jar
</code></pre>
<h4 id="custom-port">Custom Port</h4>
<p>Change the server port by passing it as a command-line argument:</p>
<pre><code class="language-bash">java -jar target/ImageMicroservice-1.0.0.jar --server.port=5500
</code></pre>
<p>Or set it in <code>application.properties</code>:</p>
<pre><code class="language-properties">server.port=5500
</code></pre>
<hr />
<h2 id="api-endpoints">API Endpoints</h2>
<table>
<thead>
<tr>
<th>Method</th>
<th>Endpoint</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>GET</td>
<td><code>/images</code></td>
<td>List all images</td>
</tr>
<tr>
<td>GET</td>
<td><code>/images/{id}</code></td>
<td>Download an image by ID</td>
</tr>
<tr>
<td>DELETE</td>
<td><code>/images/{id}</code></td>
<td>Delete an image by ID</td>
</tr>
<tr>
<td>POST</td>
<td><code>/upload</code></td>
<td>Upload a new image</td>
</tr>
<tr>
<tr>
<td>GET</td>
<td><code>/images/view</code></td>
<td>Render all images</td>
</tr>
<tr>
<td>GET</td>
<td><code>/images/view/{id}</code></td>
<td>Render a image by ID</td>
</tr>
<td>GET</td>
<td><code>/images/effect/{id}/{filter}</code></td>
<td>Apply a filter to a image by ID</td>
</tr>
<tr>
<td>GET</td>
<td><code>/images/effect/transform/{transformation}/{id}</code></td>
<td>Crop, rotate, compress/resize or add a watermark to a image by ID</td>
</tr>
</tbody>
</table>
<hr />
<h2 id="image-filters-transformations">Image Filters &amp; Transformations</h2>
<p>This service supports a variety of filters and image transformations. Below is a detailed list with usage examples.</p>
<hr />
<h3 id="filters">Filters</h3>
<table>
<thead>
<tr>
<th>Filter Name</th>
<th>Endpoint Parameter</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>Invert Color</td>
<td><code>invert-color</code></td>
<td>Inverts all colors of the image</td>
</tr>
<tr>
<td>Solarize</td>
<td><code>solarize</code></td>
<td>Applies solarization effect</td>
</tr>
<tr>
<td>Crystallize</td>
<td><code>crystallize</code></td>
<td>Pixelates the image like crystals</td>
</tr>
<tr>
<td>Emboss</td>
<td><code>emboss</code></td>
<td>Adds an embossed 3D effect</td>
</tr>
<tr>
<td>Pointillize</td>
<td><code>pointillize</code></td>
<td>Creates a pointillism effect</td>
</tr>
<tr>
<td>Halftone</td>
<td><code>halftone</code></td>
<td>Applies halftone shading effect</td>
</tr>
<tr>
<td>Water Filter</td>
<td><code>water-filter</code></td>
<td>Adds a water ripple effect</td>
</tr>
<tr>
<td>Perspective Filter</td>
<td><code>perspective-filter</code></td>
<td>Applies perspective distortion</td>
</tr>
<tr>
<td>Pinch Filter</td>
<td><code>pinch-filter</code></td>
<td>Pinches the image inward</td>
</tr>
<tr>
<td>Spherize</td>
<td><code>spherize</code></td>
<td>Creates a spherical distortion</td>
</tr>
<tr>
<td>Twirl Filter</td>
<td><code>twirl-filter</code></td>
<td>Twists the image around its center</td>
</tr>
<tr>
<td>Ripple</td>
<td><code>ripple</code></td>
<td>Adds a ripple effect</td>
</tr>
<tr>
<td>Channel Mix</td>
<td><code>channel-mix</code></td>
<td>Mixes color channels</td>
</tr>
<tr>
<td>Posterize</td>
<td><code>posterize</code></td>
<td>Reduces number of colors</td>
</tr>
<tr>
<td>RGB Adjust</td>
<td><code>rgb-adjust</code></td>
<td>Adjusts RGB channels</td>
</tr>
<tr>
<td>Grayscale</td>
<td><code>grayscale</code></td>
<td>Converts to grayscale</td>
</tr>
<tr>
<td>Convolve</td>
<td><code>convolve</code></td>
<td>Applies a convolution filter</td>
</tr>
<tr>
<td>Edge</td>
<td><code>edge</code></td>
<td>Detects edges in the image</td>
</tr>
<tr>
<td>Sharpen</td>
<td><code>sharpen</code></td>
<td>Sharpens the image</td>
</tr>
<tr>
<td>Unsharp</td>
<td><code>unsharp</code></td>
<td>Unsharp mask filter</td>
</tr>
<tr>
<td>Median</td>
<td><code>median</code></td>
<td>Median blur filter</td>
</tr>
<tr>
<td>Motion Blur</td>
<td><code>motionblur</code></td>
<td>Motion blur effect</td>
</tr>
<tr>
<td>Smart Blur</td>
<td><code>smartblur</code></td>
<td>Smart blur effect</td>
</tr>
<tr>
<td>Noise</td>
<td><code>noise</code></td>
<td>Adds noise to the image</td>
</tr>
<tr>
<td>Gaussian Blur</td>
<td><code>gaussian</code></td>
<td>Applies Gaussian blur (radius 5)</td>
</tr>
</tbody>
</table>
<p><strong>Example usage:</strong></p>
<pre><code>GET http://localhost:8080/images/effect/{id}/grayscale
</code></pre>
<hr />
<h3 id="transformations">Transformations</h3>
<table>
<thead>
<tr>
<th>Transformation</th>
<th>Method &amp; Parameters</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>Crop Image</td>
<td><code>cropImage(image, x, y, width, height)</code></td>
<td>Crops the image to the specified rectangle</td>
</tr>
<tr>
<td>Rotate Image</td>
<td><code>rotateImage(image, angle)</code></td>
<td>Rotates the image by the specified angle (degrees)</td>
</tr>
<tr>
<td>Add Watermark</td>
<td><code>addWatermark(image, watermark)</code></td>
<td>Adds a watermark text to the image</td>
</tr>
<tr>
<td>Compress / Resize</td>
<td><code>compressImage(image, scale)</code></td>
<td>Resizes the image by the given scale factor</td>
</tr>
</tbody>
</table>
<p><strong>Example usage:</strong></p>
<pre><code class="language-text">GET http://localhost:8080/images/effect/transform/resize/{id}?scale=2
GET http://localhost:8080/images/effect/transform/rotate/{id}?angle=180
GET http://localhost:8080/images/effect/transform/watermark/{id}?text=Example
GET http://localhost:8080/images/effect/transform/crop/{id}?x=250&amp;y=250&amp;width=200&amp;height=100
</code></pre>
<hr />
<h2 id="configuration">Configuration</h2>
<ul>
<li><strong>CORS</strong> is pre-configured to allow requests from <code>http://127.0.0.1:8080</code>.</li>
<li>Database file is <code>myDB.db</code> in the project root.</li>
</ul>
<hr />
<h2 id="notes">Notes</h2>
<ul>
<li>The service uses <strong>SQLite</strong>; for production, consider switching to MySQL or PostgreSQL.</li>
<li>Ensure the frontend URL matches the CORS allowed origins.</li>
</ul>
<hr />







