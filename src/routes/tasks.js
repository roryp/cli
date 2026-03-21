import { Router } from 'express';
import {
  getAllTasks,
  getTaskById,
  createTask,
  updateTask,
  deleteTask,
} from '../models/task.js';

const router = Router();

// GET /api/tasks — list all tasks (optional ?completed=true|false filter)
router.get('/', (req, res) => {
  const filter = {};
  if (req.query.completed !== undefined) {
    filter.completed = req.query.completed === 'true';
  }
  res.json(getAllTasks(filter));
});

// GET /api/tasks/:id — get a single task
router.get('/:id', (req, res) => {
  const task = getTaskById(req.params.id);
  if (!task) {
    return res.status(404).json({ error: 'Task not found' });
  }
  res.json(task);
});

// POST /api/tasks — create a new task
router.post('/', (req, res) => {
  const { title, description } = req.body;

  if (!title || typeof title !== 'string' || title.trim().length === 0) {
    return res.status(400).json({ error: 'Title is required and must be a non-empty string' });
  }

  const task = createTask({ title: title.trim(), description: description?.trim() });
  res.status(201).json(task);
});

// PUT /api/tasks/:id — update a task
router.put('/:id', (req, res) => {
  const { title, description, completed } = req.body;

  // Validate title if provided
  if (title !== undefined && (typeof title !== 'string' || title.trim().length === 0)) {
    return res.status(400).json({ error: 'Title must be a non-empty string' });
  }

  const task = updateTask(req.params.id, { title, description, completed });
  if (!task) {
    return res.status(404).json({ error: 'Task not found' });
  }
  res.json(task);
});

// PATCH /api/tasks/:id/toggle — toggle task completion
router.patch('/:id/toggle', (req, res) => {
  const task = getTaskById(req.params.id);
  if (!task) {
    return res.status(404).json({ error: 'Task not found' });
  }
  const updated = updateTask(req.params.id, { completed: !task.completed });
  res.json(updated);
});

// DELETE /api/tasks/:id — delete a task
router.delete('/:id', (req, res) => {
  const deleted = deleteTask(req.params.id);
  if (!deleted) {
    return res.status(404).json({ error: 'Task not found' });
  }
  res.status(204).end();
});

export default router;
