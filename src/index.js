import express from 'express';
import { fileURLToPath } from 'url';
import { dirname, join } from 'path';
import taskRoutes from './routes/tasks.js';
import errorHandler from './middleware/error-handler.js';

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const app = express();
const PORT = process.env.PORT || 3000;

// Parse JSON request bodies
app.use(express.json());

// Serve the frontend from /public
app.use(express.static(join(__dirname, '..', 'public')));

// API routes
app.use('/api/tasks', taskRoutes);

// Health check
app.get('/api/health', (_req, res) => {
  res.json({ status: 'ok', timestamp: new Date().toISOString() });
});

// Centralized error handling
app.use(errorHandler);

app.listen(PORT, () => {
  console.log(`🚀 Vibe Task Tracker running at http://localhost:${PORT}`);
  console.log(`📋 API available at http://localhost:${PORT}/api/tasks`);
});

export default app;
