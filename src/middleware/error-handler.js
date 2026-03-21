/**
 * Centralized error-handling middleware.
 * Catches unhandled errors and returns a consistent JSON response.
 */
const errorHandler = (err, req, res, _next) => {
  console.error(`[ERROR] ${req.method} ${req.path}:`, err.message);

  const statusCode = err.statusCode || 500;
  const message =
    process.env.NODE_ENV === 'production'
      ? 'Internal server error'
      : err.message;

  res.status(statusCode).json({ error: message });
};

export default errorHandler;
