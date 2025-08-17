<h1 id="image-microservice-api">Image Processing API</h1>
<p>A lightweight microservice for storing and serving image files with database persistence. Built with <strong>Java</strong>, <strong>Spring Boot</strong>, and <strong>SQLite</strong>.</p>
<hr />
<h2 id="features">Features</h2>
<ul>
<li>Upload, download, and manage images.</li>
<li>Stores images in a database.</li>
<li>Configurable CORS for frontend integration.</li>
<li>Automatically generates database tables.</li>
<li>Users can interact with the database using the H2 console, to use it run the app and open <code>http://localhost:{port}/h2-console</code> in the browser.</li>
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





