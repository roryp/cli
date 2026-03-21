import { v4 as uuidv4 } from 'uuid';

/** @type {Map<string, import('./task.js').Task>} */
const tasks = new Map();

/**
 * @typedef {Object} Task
 * @property {string} id - Unique identifier
 * @property {string} title - Task title
 * @property {string} [description] - Optional description
 * @property {boolean} completed - Whether the task is done
 * @property {string} createdAt - ISO timestamp
 * @property {string} updatedAt - ISO timestamp
 */

/**
 * Get all tasks, optionally filtered by completion status.
 * @param {{ completed?: boolean }} [filter]
 * @returns {Task[]}
 */
export const getAllTasks = (filter = {}) => {
  let result = [...tasks.values()];

  if (filter.completed !== undefined) {
    result = result.filter((t) => t.completed === filter.completed);
  }

  return result.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
};

/**
 * Find a task by ID.
 * @param {string} id
 * @returns {Task | undefined}
 */
export const getTaskById = (id) => tasks.get(id);

/**
 * Create a new task.
 * @param {{ title: string, description?: string }} data
 * @returns {Task}
 */
export const createTask = ({ title, description = '' }) => {
  const now = new Date().toISOString();
  const task = {
    id: uuidv4(),
    title,
    description,
    completed: false,
    createdAt: now,
    updatedAt: now,
  };
  tasks.set(task.id, task);
  return task;
};

/**
 * Update an existing task.
 * @param {string} id
 * @param {{ title?: string, description?: string, completed?: boolean }} updates
 * @returns {Task | null}
 */
export const updateTask = (id, updates) => {
  const task = tasks.get(id);
  if (!task) return null;

  const allowed = ['title', 'description', 'completed'];
  for (const key of allowed) {
    if (updates[key] !== undefined) {
      task[key] = updates[key];
    }
  }
  task.updatedAt = new Date().toISOString();
  tasks.set(id, task);
  return task;
};

/**
 * Delete a task by ID.
 * @param {string} id
 * @returns {boolean} True if deleted, false if not found
 */
export const deleteTask = (id) => tasks.delete(id);
